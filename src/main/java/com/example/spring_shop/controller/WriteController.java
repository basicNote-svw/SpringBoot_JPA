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

    @PostMapping("/add")
    // 방법1
//    String writePost(@RequestParam String title, @RequestParam Integer price) {
//        System.out.println(title + " " + price);
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);
//        return "redirect:/list";
//    }
    // 방법2
//    String writePost(@RequestParam Map formData) {
////        var test = new HashMap<>();
//        HashMap<String, Object> test = new HashMap<>();
//        test.put("title", "모자");
//        test.put("price", 10000);
//        System.out.println(test);   // {price=10000, title=모자}
//        System.out.println(test.get("title"));  // 모자
//        return "redirect:/list";
//    }
    // 방법3
    String writePost(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/list";
    }
}
