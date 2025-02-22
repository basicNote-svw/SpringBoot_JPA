package com.example.spring_shop.member;

public class MemberDTO {
    public String username;
    public String displayName;

    MemberDTO(String a, String b) {
        this.username = a;
        this.displayName = b;
    }
}
