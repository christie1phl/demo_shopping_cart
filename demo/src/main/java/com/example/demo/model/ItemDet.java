package com.example.demo.model;

import lombok.*;

@Data
public class ItemDet {

    private int id;
    private String itemCode;
    private String itemNameEn;
    private String itemNameAr;
    private String itemDesc;
    private String itemType;
    private double pricePerUnit;
}
