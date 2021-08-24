package com.example.ddd.schedules.schedule.domain;

import com.example.ddd.schedules.pomodoro.domain.Pomodoro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleTest {

    @DisplayName("일정은 하나 이상의 뽀모도로를 가진다.")
    @Test
    void pomodros() {
        List<Pomodoro> pomodoros = List.of(Pomodoro.of("공부하기"), Pomodoro.of("운동하기"));
        Schedule schedule = Schedule.of(1L, pomodoros);

        assertThat(schedule.getPomodoros().size()).isGreaterThan(1);
    }

    @DisplayName("참가한 사용자 정보를 가진다.")
    @Test
    void has_user_id() {
        List<Pomodoro> pomodoros = List.of(Pomodoro.of("공부하기"));
        Schedule schedule = Schedule.of(1L, pomodoros);

        assertThat(schedule.getUserId()).isNotNull();
    }
}
