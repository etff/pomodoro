package com.example.ddd.schedules.pomodoro.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class PomodoroTest {

    @DisplayName("뽀모도로 초기상태는 중지상태이다.")
    @Test
    void init_status() {
        String todo = "공부하기";
        Pomodoro pomodoro = new Pomodoro(todo);

        assertThat(pomodoro.getTimeStatus()).isEqualTo(TimeStatus.STOP);
    }

    @DisplayName("할 일을 가지고 있다.")
    @Test
    void todo() {
        String todo = "공부하기";
        Pomodoro pomodoro = new Pomodoro(todo);

        assertThat(pomodoro.getTodo()).isEqualTo(todo);
    }

    @TestFactory
    Collection<DynamicTest> 뽀모도로_상태에_맞춰_남은_시간이_설정된다() {
        String todo = "공부하기";
        Pomodoro pomodoro = new Pomodoro(todo);

        return Arrays.asList(
                DynamicTest.dynamicTest("뽀모도로를 초기상태의 남은 시간은 0이다.", () -> {
                    assertThat(pomodoro.getRemainTime()).isZero();
                }),
                DynamicTest.dynamicTest("뽀모도로 집중 상태를 설정하고 남은 시간을 확인할 수 있다.", () -> {
                    pomodoro.start(TimeStatus.WORK_SESSION);
                    assertThat(pomodoro.getRemainTime()).isEqualTo(25L);
                })
        );
    }
}
