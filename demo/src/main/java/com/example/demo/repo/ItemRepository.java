package com.example.demo.repo;

import com.example.demo.enitity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ItemRepository extends JpaRepository<Item,Long> {


}
