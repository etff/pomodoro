package com.example.ddd.schedules.schedule.ui;

import com.example.ddd.schedules.schedule.application.ScheduleService;
import com.example.ddd.schedules.schedule.dto.ScheduleRequest;
import com.example.ddd.schedules.schedule.dto.ScheduleResponse;
import com.example.ddd.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserResponse> createSchedule(@PathVariable Long userId, ScheduleRequest request) {
        ScheduleResponse response = scheduleService.registerSchedule(userId, request);
        return ResponseEntity.created(URI.create("/schedules/" + userId + "/" + response.getId())).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
