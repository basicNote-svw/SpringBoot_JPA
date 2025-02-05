package com.example.spring_shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(String username, String password, String displayName) throws Exception {
        var result = memberRepository.findByUsername(username);
        if(result.isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다");
        }
        if(username.length() < 8 || password.length() < 8) {
            throw new Exception("8자 이상 입력하세요");
        }
        Member member = new Member();
        member.setUsername(username);
//        var hash = new BCryptPasswordEncoder().encode(password);
        var hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
