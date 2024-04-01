package com.example.demo.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Table(name = "CustOrder")
@NoArgsConstructor
public class CustOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade=ALL, mappedBy="order")
    private List<OrderDetail> orderLst;

    @Column(name = "status")
    private String status;

    @Column(name = "total_cost")
    private BigDecimal totalCost;


    @Column(name = "discounted_amount")
    private BigDecimal discountedAmount;

    private BigDecimal totalGrocCost = BigDecimal.ZERO;
    private BigDecimal totalNoGrocCost = BigDecimal.ZERO;


    public CustOrder(List<OrderDetail> orderLst) {
        this.orderLst = orderLst;
    }

}
