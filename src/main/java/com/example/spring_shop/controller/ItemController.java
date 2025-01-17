package com.example.spring_shop.controller;

import com.example.spring_shop.entity.Item;
import com.example.spring_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        System.out.println(result); // [com.example.spring_shop.entity.Item@6e8e6f05, com.example.spring_shop.entity.Item@679ad4a3, com.example.spring_shop.entity.Item@6cf1174]
        System.out.println(result.get(0));          // com.example.spring_shop.entity.Item@6f2a15fe
//        System.out.println(result.get(0).title);    // 셔츠
        System.out.println(result.get(0).getTitle());   // 셔츠
//        model.addAttribute("name", "롱스커트");
        model.addAttribute("items", result);
        return "list";
    }

//    // Lombok 사용 X 시
//    @Autowired
//    ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()) {
            System.out.println(result.get());
            model.addAttribute("data", result.get());
            return "detail";
        } else {
            return "redirect:/list";
        }
    }

}

