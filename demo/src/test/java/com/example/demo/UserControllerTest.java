package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.enitity.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void create_shouldCreateSuccessfully()
    {
        SignUpDTO signUpDTO = new SignUpDTO("Jack","jack","jack@gmail.com","jackPass","ROLE_CUSTOMER");
        when(userService.registerUser(signUpDTO)).thenReturn(new Customer("jack","jackPass","Jack","jack@gmail.com","",new Date()));
        ResponseEntity<Customer> resp = userController.registerUser(signUpDTO);
        Customer cust = resp.getBody();
        assertThat(resp.getStatusCode().value() ==  200);
    }
}
