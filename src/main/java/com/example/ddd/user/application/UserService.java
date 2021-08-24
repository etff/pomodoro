package com.example.ddd.user.application;

import com.example.ddd.user.domain.User;
import com.example.ddd.user.dto.UserRequest;
import com.example.ddd.user.dto.UserResponse;
import com.example.ddd.user.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private static final String ALREADY_EXIST_NAME = "이미 존재하는 사용자입니다 : ";
    private static final String NOT_EXIST_USER = "존재하지 않는 사용자: ";

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest dto) {
        User user = dto.toEntity();

        checkUserExist(user);
        return UserResponse.of(userRepository.save(user));
    }

    public UserResponse getUser(Long id) {
        User user = findUser(id);
        return UserResponse.of(user);
    }

    public UserResponse modifyUser(Long id, UserRequest dto) {
        User user = findUser(id);
        user.changeWith(dto.getName());
        return UserResponse.of(user);
    }

    public void deleteUser(Long id) {
        User user = findUser(id);
        userRepository.delete(user);
    }

    private void checkUserExist(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(u -> {
                    throw new EntityExistsException(ALREADY_EXIST_NAME + user.getName());
                });
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_USER + id));
    }
}
