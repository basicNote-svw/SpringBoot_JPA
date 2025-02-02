package com.example.spring_shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/member")
    public String addMember(String userName, String password, String displayName) throws Exception {
//        Member member = new Member();
//        member.setUserName(userName);
////        var hash = new BCryptPasswordEncoder().encode(password);
//        var hash = passwordEncoder.encode(password);
//        member.setPassword(hash);
//        member.setDisplayName(displayName);
//        memberRepository.save(member);
        memberService.saveMember(userName, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
