package com.example.spring_shop.controller;

import com.example.spring_shop.entity.Item;
import com.example.spring_shop.repository.ItemRepository;
import com.example.spring_shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
//@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Autowired
    ItemController(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

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
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "404 에러 발생"
            );
        }
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
//    String writePost(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "redirect:/list";
//    }
    String writePost(String title, Integer price) {
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

}

