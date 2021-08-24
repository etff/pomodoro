package com.example.ddd.schedules.pomodoro.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeStatusTest {

    @DisplayName("뽀모도르는 중지, 집중시간, 쉬는시간 상태를 갖고있다.")
    @Test
    void status() {
        assertThat(TimeStatus.values()).containsExactlyInAnyOrder(
                TimeStatus.STOP, TimeStatus.BREAKS, TimeStatus.WORK_SESSION
        );
    }

    @DisplayName("집중시간은 25분으로 이뤄져있다.")
    @Test
    void work_session() {
        assertThat(TimeStatus.WORK_SESSION.getTime()).isEqualTo(25);
    }

    @DisplayName("쉬는시간은 5분으로 이뤄져있다.")
    @Test
    void breaks() {
        assertThat(TimeStatus.BREAKS.getTime()).isEqualTo(5);
    }

    @DisplayName("중지시간은 0분으로 이뤄져있다.")
    @Test
    void stop() {
        assertThat(TimeStatus.STOP.getTime()).isZero();
    }

}
