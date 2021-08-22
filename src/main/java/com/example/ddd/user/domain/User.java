package com.example.ddd.user.domain;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Entity
public class User {
    private static final String NAME_BLANK = "이름은 빈 값이 될 수 없습니다.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    protected User() {
    }

    private User(String name, UserRole userRole) {
        this.name = new Name(name);
        this.userRole = userRole;
    }

    public static User ofNormalUser(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_BLANK);
        }
        return new User(name, UserRole.NORMAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void changeWith(String name) {
        this.name = new Name(name);
    }
}
