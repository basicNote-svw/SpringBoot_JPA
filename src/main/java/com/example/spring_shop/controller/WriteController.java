package com.example.spring_shop.controller;

import com.example.spring_shop.entity.Item;
import com.example.spring_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WriteController {

    private final ItemRepository itemRepository;

    @GetMapping("/write")
    String write() {
        return "write";
    }

}
