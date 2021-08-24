package com.example.ddd.schedules.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleRequest {
    private Long userId;
    private List<String> todos;

    public ScheduleRequest(Long userId, List<String> todos) {
        this.userId = userId;
        this.todos = todos;
    }
}
