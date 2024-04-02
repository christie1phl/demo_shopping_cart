package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.enitity.*;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;


    @Test
    @Order(1)
    void create_shouldCreateSuccessfully()
    {
        SignUpDTO signUpDTO = new SignUpDTO("Rony","rony","ronshabby@gmail.com","ronyPass","ROLE_CUSTOMER");
        when(userService.registerUser(signUpDTO)).thenReturn(new Customer("Rony","ronyPass","Rony","ronshabby@gmail.com","",new Date()));
        ResponseEntity<Customer> resp = userController.registerUser(signUpDTO);
        Customer cust = resp.getBody();
        assertThat(resp.getStatusCode().value() ==  200);
    }


    @Test
    @Order(2)
    void login_shouldloginSuccessfully()
    {

        MockHttpServletRequest request = new MockHttpServletRequest();
        LoginDTO loginDTO = new LoginDTO("rony","ronyPass");

        when(authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())))
                .thenReturn(new TestingAuthenticationToken("rony,","ronyPass","ROLE_CUSTOMER"));
        userController.authenticateUser(loginDTO,request);


        ResponseEntity<String> resp = userController.authenticateUser(loginDTO, request);

        assertThat(resp.getStatusCode().value() ==  200);
    }
}
