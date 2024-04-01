package com.example.demo.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;

@Entity
@Getter
@Setter
@Table(name = "OrderDetail")
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Item item;

    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne
    private CustOrder order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public OrderDetail(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
