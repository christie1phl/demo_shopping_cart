package com.example.demo.service;

import com.example.demo.enitity.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public CustOrder processOrder(CustOrder custOrder, String userRole, String username){
        //Gathering and costing non groc Items

        custOrder.getOrderLst().stream().forEach(orderDet -> {
            Item item = itemRepository.findById(orderDet.getItem().getId()).get();
            orderDet.setItem(item);
            //Cal total item cost
            BigDecimal totalItemPrice = item.getPricePerUnit().multiply(
                    new BigDecimal(orderDet.getQuantity()));
            orderDet.setTotalPrice(totalItemPrice);
            if(item.getItemType().equalsIgnoreCase("grocery")){
                custOrder.setTotalGrocCost(custOrder.getTotalGrocCost().add(totalItemPrice));
            }else{
                custOrder.setTotalNoGrocCost(custOrder.getTotalNoGrocCost().add(totalItemPrice));
            }
        });
        //Applying discounts on non groc

        BigDecimal nonGrocTotCost = Optional.ofNullable(custOrder.getTotalNoGrocCost()).orElse(BigDecimal.ZERO);
        BigDecimal grocTotCost = Optional.ofNullable(custOrder.getTotalGrocCost()).orElse(BigDecimal.ZERO);

         switch(userRole){
            case "ROLE_EMPLOYEE":
                nonGrocTotCost = nonGrocTotCost.subtract(nonGrocTotCost.multiply(new BigDecimal(0.3)));
                break;
            case "ROLE_CUSTOMER":
                Customer customer = userRepository.findByUsernameOrEmail(username,username);
                Calendar twoYearsAgo = GregorianCalendar.getInstance();
                twoYearsAgo.add(Calendar.YEAR,-2);
                if(customer.getCreationDate().getTime() < twoYearsAgo.getTimeInMillis()) {
                    nonGrocTotCost = nonGrocTotCost.subtract(nonGrocTotCost.multiply(new BigDecimal(0.5)));
                }
                break;
            case "ROLE_PARTNER":
                nonGrocTotCost = nonGrocTotCost.subtract(nonGrocTotCost.multiply(new BigDecimal(0.1)));
                break;
        }
        BigDecimal totalCost = nonGrocTotCost.add(grocTotCost);
        custOrder.setTotalCost(totalCost.setScale(2, RoundingMode.HALF_UP));
        BigDecimal disCountedTotal = totalCost.divide(new BigDecimal(100))
                .setScale(0,RoundingMode.HALF_EVEN).multiply(new BigDecimal(5));
        custOrder.setDiscountedAmount(totalCost.subtract(disCountedTotal).setScale(2, RoundingMode.HALF_UP));

      //  orderRepository.saveAndFlush(custOrder);
        return custOrder;
    }

}
