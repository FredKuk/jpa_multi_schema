package com.example.demo3.tony;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.Good;

public interface GoodTonyDao extends JpaRepository<Good,Integer>{
    
}
