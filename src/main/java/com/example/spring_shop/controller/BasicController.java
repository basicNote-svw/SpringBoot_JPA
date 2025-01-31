package com.example.spring_shop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller
public class BasicController {
    @GetMapping("/")
//    @ResponseBody
    String hello() {
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String hello2() {
        return "피싱사이트에요";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String hello3() {
        return "마이페이지입니다";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
//        return LocalDateTime.now().toString();
        return ZonedDateTime.now().toString();
    }

    @GetMapping("/test2")
    String test2() {
        var encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("qwer1234"));
        return "redirect:/list";
    }
}
