package com.example.spring_shop.controller;

import com.example.spring_shop.entity.Notice;
import com.example.spring_shop.repository.NoticeRepository;
import com.example.spring_shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class NoticeController {
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;

    @Autowired
    NoticeController(NoticeRepository noticeRepository, NoticeService noticeService) {
        this.noticeRepository = noticeRepository;
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    String notice(Model model) {
//        List<Notice> result = noticeRepository.findAll();
        List<Notice> result = noticeService.getAllNotices();
//        System.out.println(result.get(0).title);   // 이번 주 할인이벤트합니다
        System.out.println(result.get(0).getTitle());   // 이번 주 할인이벤트합니다
        model.addAttribute("notices", result);
        return "notice";
    }
}
