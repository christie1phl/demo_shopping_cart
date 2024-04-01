package com.example.demo.repo;

import com.example.demo.enitity.*;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsernameOrEmail(String username, String email);
}