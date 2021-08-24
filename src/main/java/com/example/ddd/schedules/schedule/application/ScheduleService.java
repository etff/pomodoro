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

import static java.util.function.Predicate.not;

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
        return todos.stream()
                .filter(not(String::isBlank))
                .map(Pomodoro::new)
                .collect(Collectors.toList());
    }
}
