package com.example.ddd.user.ui;

import com.example.ddd.user.application.UserService;
import com.example.ddd.user.dto.UserRequest;
import com.example.ddd.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(UserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.getId())).build();
    }
}
