package com.example.spring_shop.controller;

import com.example.spring_shop.entity.Item;
import com.example.spring_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        System.out.println(result); // [com.example.spring_shop.entity.Item@6e8e6f05, com.example.spring_shop.entity.Item@679ad4a3, com.example.spring_shop.entity.Item@6cf1174]
        System.out.println(result.get(0));          // com.example.spring_shop.entity.Item@6f2a15fe
        System.out.println(result.get(0).title);    // 셔츠
        model.addAttribute("name", "롱스커트");
        return "list";
    }

//    // Lombok 사용 X 시
//    @Autowired
//    ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

}

