package com.example.demo.model;

import lombok.*;

import java.util.*;

@Data
public class OrderDet {

    private int id;
    private List<ItemDet> items;
    private double totalAmount;
    private double discountAmount;

}

