package com.example.ddd.schedules.schedule.application;

import com.example.ddd.schedules.pomodoro.domain.Pomodoro;
import com.example.ddd.schedules.schedule.domain.Schedule;
import com.example.ddd.schedules.schedule.dto.ScheduleRequest;
import com.example.ddd.schedules.schedule.dto.ScheduleResponse;
import com.example.ddd.schedules.schedule.infra.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        scheduleService = new ScheduleService(scheduleRepository);
    }

    @DisplayName("일정을 등록할 수 있다.")
    @Test
    void registerSchedule() {
        // given
        Long userId = 1L;
        List<String> todos = List.of("공부하기", "배포하기");
        ScheduleRequest request = new ScheduleRequest(userId, todos);

        // when
        ScheduleResponse schedule = scheduleService.registerSchedule(userId, request);

        // then
        assertThat(schedule).isNotNull();
    }

    @DisplayName("일정을 삭제할 수 있다.")
    @Test
    void deleteSchedule() {
        // given
        Long givenId;
        List<Pomodoro> pomodros = List.of(Pomodoro.of("공부하기"));
        givenId = scheduleRepository.save(Schedule.of(1L, pomodros))
                .getId();
        // when
        scheduleService.deleteSchedule(givenId);

        // then
        assertThat(scheduleRepository.findById(givenId)).isEmpty();
    }
}
