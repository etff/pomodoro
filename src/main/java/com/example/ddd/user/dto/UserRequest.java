package com.example.ddd.user.dto;

import com.example.ddd.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private String name;

    public UserRequest(String name) {
        this.name = name;
    }

    public User toEntity() {
        return User.ofNormalUser(name);
    }
}
