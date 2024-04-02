package com.example.demo.controller;

import com.example.demo.enitity.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


/**
 * A Controller to process sign up and login
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto,HttpServletRequest req) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY,  SecurityContextHolder.getContext());
        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
    }


    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public ResponseEntity<Customer> registerUser(@RequestBody SignUpDTO signUpDto){


        Customer cust = userService.registerUser(signUpDto);
        return  ResponseEntity.ok(cust);

    }
}
