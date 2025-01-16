package com.example.spring_shop.entity;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 200)
    public String title;

    @Column(nullable = false)
    public Integer price;
}
