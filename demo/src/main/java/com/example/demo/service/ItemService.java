package com.example.demo.service;

import com.example.demo.enitity.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> fetchItems(){
        return itemRepository.findAll();
    }


    public void createItems(List items){
        itemRepository.saveAllAndFlush(items);
    }
}
