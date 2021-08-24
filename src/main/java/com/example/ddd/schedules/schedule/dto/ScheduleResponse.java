package com.example.ddd.schedules.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponse {
    private Long id;

    @Builder
    public ScheduleResponse(Long id) {
        this.id = id;
    }
}
