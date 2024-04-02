package com.example.demo.service;

import com.example.demo.enitity.*;
import com.example.demo.repo.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import java.math.*;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @InjectMocks
    OrderService orderService;

    @Mock
    UserRepository userRepository;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void createOrder_apply_discount_successfully_customer(){

        CustOrder responseOrder = prepareTestOrder("ROLE_CUSTOMER");
        assertThat(responseOrder.getDiscountedAmount().equals(new BigDecimal(200)));

        responseOrder = prepareTestOrder("ROLE_PARTNER");
        assertThat(responseOrder.getDiscountedAmount().equals(new BigDecimal(200)));

        responseOrder = prepareTestOrder("ROLE_EMPLOYEE");
        assertThat(responseOrder.getDiscountedAmount().equals(new BigDecimal(200)));
    }


    private CustOrder prepareTestOrder(String role){
        List<Item> items = List
                .of(new Item("100","Apples","Apples",
                                "fruits","grocery",new BigDecimal(200)),
                        new Item("100","Oranges","Oranges",
                                "fruits","grocery",new BigDecimal(400)),
                        new Item("100","Shoes","Shoes",
                                "Footwear","Footwear",new BigDecimal(600)),
                        new Item("100","Lipstick","Lipstick",
                                "Cosmetics","Cosmetics",new BigDecimal(1000)),
                        new Item("100","Skirt","Skirt",
                                "Clothes","Clothes",new BigDecimal(400)));
        when(itemRepository.findById(any())).thenReturn(Optional.ofNullable(items.get(0)));
        Customer cust = new Customer("rony","ronyPass","Rony","ronShabby@gmail.com","",new Date());
        cust.setRoles(Set.of(new Roles(role)));
        if(role.equalsIgnoreCase("ROLE_CUSTOMER")) {
            when(userRepository.findByUsernameOrEmail("rony", "rony")).thenReturn(
                    cust);
        }
        List<OrderDetail> lst = List.of(new OrderDetail(new Item(3),4),
                new OrderDetail(new Item(2),4),
                new OrderDetail(new Item(1),4));
        CustOrder responseOrder = orderService.processOrder(new CustOrder(lst),role,"rony");
        return responseOrder;
    }
}
