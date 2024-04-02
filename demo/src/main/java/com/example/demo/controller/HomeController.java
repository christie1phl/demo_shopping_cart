package com.example.demo.controller;

import com.example.demo.enitity.*;
import com.example.demo.service.*;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;
import java.math.*;
import java.util.*;


/**
 * Loads the Shopping cart items
 */
@RestController
public class HomeController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)

    public ResponseEntity<String> home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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

        itemService.createItems(items);
        List<Item> lst = itemService.fetchItems();
        Gson gson = new Gson();
        String json = gson.toJson(lst);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
                new String[]{MediaType.APPLICATION_JSON_VALUE}).body(json);
    }


}
