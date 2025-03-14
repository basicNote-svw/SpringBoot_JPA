package com.example.spring_shop.comment;

import com.example.spring_shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    String postComment(@RequestParam String content, @RequestParam Long parentId, Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();

        var data = new Comment();
        data.setContent(content);
        data.setUsername(user.getUsername());
        data.setParentId(parentId);
        commentRepository.save(data);
        System.out.println("댓글 저장 완료: " + data.getId());
        return "redirect:/list";
    }
}
