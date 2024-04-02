package com.example.demo.controller;

import com.example.demo.enitity.*;
import com.example.demo.repo.*;
import com.example.demo.service.*;
import com.google.gson.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;


/**
 * A rest controller to process the customer order and calculate the discount
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustOrder> createOrder(HttpServletRequest request, @RequestBody CustOrder custOrder,@AuthenticationPrincipal UserDetails userDetails ){
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        custOrder = orderService.processOrder(custOrder,role,userDetails.getUsername());

        return ResponseEntity.ok().body(custOrder);
    }


}
