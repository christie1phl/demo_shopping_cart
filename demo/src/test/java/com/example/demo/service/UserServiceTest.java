package com.example.demo.service;


import com.example.demo.enitity.*;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;

import java.nio.file.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void loaduserByUsername_checkSuccess(){
       Customer customer =  new Customer("Rony","ronyPass",
                "Rony","ronshabby@gmail.com","",new Date());
       customer.setRoles(Set.of(new Roles("ROLE_EMPLOYEE")));
       when (userRepository.findByUsernameOrEmail("rony","rony"))
               .thenReturn(customer);
        UserDetails userDetails = userService.loadUserByUsername("rony");
        assertThat(!userDetails.getAuthorities().isEmpty());
    }


    @Test
    public void signup_checkSuccess(){
        SignUpDTO signUpDTO = new SignUpDTO("Rony","rony","ronshabby@gmail.com","ronyPass","ROLE_CUSTOMER");

        when (roleRepository.findByName(signUpDTO.getRole()))
                .thenReturn(Optional.of(new Roles("ROLE_CUSTOMER")));

        when(passwordEncoder.encode(signUpDTO.getPassword())).thenReturn("xyz");
        Customer customer = userService.registerUser(signUpDTO);
        assertThat(customer.getCreationDate());
    }

}
