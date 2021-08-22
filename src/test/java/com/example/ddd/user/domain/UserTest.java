package com.example.ddd.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("사용자는 이름을 가지고 있다.")
    @Test
    void name() {
        String name = "user1";
        User user = User.ofNormalUser(name);

        assertThat(user.getName()).isEqualTo(new Name(name));
    }

    @DisplayName("사용자는 이름을 가지고 있다.")
    @Test
    void changeInfo() {
        String name = "user1";
        User user = User.ofNormalUser(name);

        String updateName = "user2";
        user.changeWith(updateName);

        assertThat(user.getName()).isEqualTo(new Name(updateName));
    }
}
