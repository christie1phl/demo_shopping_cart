package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.enitity.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.context.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;



    @Test
    public void createordercustomer_createSuccessfully(){
        CustOrder custOrder = new CustOrder(List.of(
                new OrderDetail(new Item(3),4),  new OrderDetail(new Item(2),4),  new OrderDetail(new Item(1),4)));
        MockHttpServletRequest request = new MockHttpServletRequest();
        when(orderService.processOrder(custOrder,"ROLE_EMPLOYEE","rony")).thenReturn(custOrder);
        ResponseEntity<CustOrder> resp = orderController.
                createOrder(request, custOrder, new User("rony","ronyPass",
                        List.of(new GrantedAuthority(){
                            @Override
                            public String getAuthority() {
                                return "ROLE_EMPLOYEE";
                            }
                        })));
        assertThat(resp.getStatusCode().value()==200);
        assertThat(resp.getBody().getDiscountedAmount() != null);
    }

}
