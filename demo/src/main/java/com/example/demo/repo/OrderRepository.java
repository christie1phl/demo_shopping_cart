package com.example.demo.repo;

import com.example.demo.enitity.*;
import org.springframework.data.jpa.repository.*;

public interface OrderRepository extends JpaRepository<CustOrder,Long> {
}
