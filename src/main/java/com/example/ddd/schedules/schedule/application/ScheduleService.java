package com.example.ddd.schedules.schedule.application;

import com.example.ddd.schedules.pomodoro.domain.Pomodoro;
import com.example.ddd.schedules.schedule.domain.Schedule;
import com.example.ddd.schedules.schedule.dto.ScheduleRequest;
import com.example.ddd.schedules.schedule.dto.ScheduleResponse;
import com.example.ddd.schedules.schedule.infra.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse registerSchedule(Long userId, ScheduleRequest dto) {
        final List<Pomodoro> pomodoros = createPomodoros(dto.getTodos());
        final Schedule schedule = Schedule.of(userId, pomodoros);
        final Schedule saved = scheduleRepository.save(schedule);
        return ScheduleResponse.builder()
                .id(saved.getId())
                .build();
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    private List<Pomodoro> createPomodoros(List<String> todos) {
        checkTodoBlank(todos);
        return todos.stream()
                .map(Pomodoro::new)
                .collect(Collectors.toList());
    }

    private void checkTodoBlank(List<String> todos) {
        for (String todo : todos) {
            if (todo.isBlank()) {
                throw new IllegalArgumentException("할일이 있어야합니다.");
            }
        }
    }
}
