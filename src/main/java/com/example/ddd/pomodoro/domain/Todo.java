package com.example.ddd.pomodoro.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Todo {
    private String todo;

    protected Todo() {
    }

    protected Todo(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }
}
