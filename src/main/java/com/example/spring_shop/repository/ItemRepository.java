package com.example.spring_shop.repository;

import com.example.spring_shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
