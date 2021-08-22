package com.example.ddd.user.dto;

import com.example.ddd.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String name;

    public UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName().getName());
    }
}
