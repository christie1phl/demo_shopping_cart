package com.example.demo.enitity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Roles{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Roles() {

    }

    public Roles(String name){
      this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}