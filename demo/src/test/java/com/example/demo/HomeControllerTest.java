package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.enitity.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;

import java.math.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private ItemService itemService;

    @Test
    public void testFetchCartItems(){
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
        when(itemService.fetchItems()).thenReturn(items);
        ResponseEntity<String> response =  homeController.home();
        assertThat(response.getStatusCode().value() ==  200);
    }
}
