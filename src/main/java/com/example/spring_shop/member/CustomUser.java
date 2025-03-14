package com.example.spring_shop.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUser extends User {
    public String displayName;
    public CustomUser(String username, String password, List<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
