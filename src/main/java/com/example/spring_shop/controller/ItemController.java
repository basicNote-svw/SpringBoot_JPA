package com.example.spring_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("name", "롱스커트");
        return "list";
    }
}
