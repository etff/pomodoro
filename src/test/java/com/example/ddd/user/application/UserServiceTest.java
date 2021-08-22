package com.example.ddd.user.application;

import com.example.ddd.user.domain.Name;
import com.example.ddd.user.domain.User;
import com.example.ddd.user.dto.UserRequest;
import com.example.ddd.user.dto.UserResponse;
import com.example.ddd.user.infra.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @TestFactory
    Collection<DynamicTest> 사용자_등록_시나리오() {
        return Arrays.asList(
                DynamicTest.dynamicTest("사용자를 등록할 수 있다.", () -> {
                    UserRequest dto = new UserRequest("user1");
                    userService.createUser(dto);

                    Optional<User> findUser = userRepository.findByName(new Name(dto.getName()));
                    assertThat(findUser.isPresent()).isTrue();
                }),
                DynamicTest.dynamicTest("사용자 이름은 중복될 수 없다. (throw EntityExistException", () -> {
                    assertThatThrownBy(() -> userService.createUser(new UserRequest("user1")))
                            .isInstanceOf(EntityExistsException.class);
                })
        );
    }

    @DisplayName("사용자를 조회할 수 있다.")
    @Test
    void getUser() {
        // given
        Long givenId = 1L;
        userRepository.save(User.ofNormalUser("user1"));

        // when
        UserResponse actual = userService.getUser(givenId);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("사용자를 삭제할 수 있다.")
    @Test
    void deleteUser() {
        // given
        Long givenId;
        givenId = userRepository.save(User.ofNormalUser("user1")).getId();

        // when
        userService.deleteUser(givenId);

        // then
        Optional<User> user = userRepository.findById(givenId);
        assertThat(user.isEmpty()).isTrue();
    }

    @DisplayName("사용자를 수정할 수 있다.")
    @Test
    void modifyUser() {
        // given
        Long givenId;
        givenId = userRepository.save(User.ofNormalUser("user1")).getId();
        UserRequest userRequest = new UserRequest("user2");

        // when
        userService.modifyUser(givenId, userRequest);

        // then
        User user = userRepository.findById(givenId)
                .orElseThrow(() -> new EntityNotFoundException());
        assertThat(user.getName()).isEqualTo(new Name("user2"));
    }
}
