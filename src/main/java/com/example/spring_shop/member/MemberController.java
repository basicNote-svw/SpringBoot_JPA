package com.example.spring_shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/member")
    public String addMember(String username, String password, String displayName) throws Exception {
//        Member member = new Member();
//        member.setUsername(username);
////        var hash = new BCryptPasswordEncoder().encode(password);
//        var hash = passwordEncoder.encode(password);
//        member.setPassword(hash);
//        member.setDisplayName(displayName);
//        memberRepository.save(member);
        memberService.saveMember(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
//        var result = memberRepository.findByUsername("rkskekfk");
//        System.out.println(result.get().getDisplayName());
        return "login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        System.out.println(auth);
        System.out.println(auth.getName()); //아이디출력가능
        System.out.println(auth.isAuthenticated()); //로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));

        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        return "mypage";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDTO getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new MemberDTO(result.getUsername(), result.getDisplayName());
        return data;    // object 여기 넣으면 스프링이 자동으로 JSON으로 변환해줌
    }

}
