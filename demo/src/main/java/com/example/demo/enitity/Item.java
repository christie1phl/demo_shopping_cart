package com.example.demo.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "item_code", nullable = false)
    private String itemCode;
    @Column(name = "item_name_en", nullable = false)
    private String itemNameEn;
    @Column(name = "item_name_ar", nullable = false)
    private String itemNameAr;
    @Column(name = "item_desc", nullable = false)
    private String itemDesc;
    @Column(name = "item_type", nullable = false)
    private String itemType;
    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    public Item(String itemCode, String itemNameEn, String itemNameAr, String itemDesc, String itemType, BigDecimal pricePerUnit) {
    this.itemCode = itemCode;
    this.itemNameEn = itemNameEn;
    this.itemNameAr = itemNameAr;
    this.itemDesc = itemDesc;
    this.itemType = itemType;
    this.pricePerUnit = pricePerUnit;
    }

    public Item(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
