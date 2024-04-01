package com.example.demo.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Usertype")
public class Usertype {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_type")
    private String usertype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Customer> user;
}
