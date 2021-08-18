package com.example.ddd.pomodoro.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RemainTime {
    private Long remainTime;

    @Transient
    private LocalDateTime startTime;

    @Transient
    private LocalDateTime endTime;

    protected RemainTime() {
    }

    protected RemainTime(TimeStatus timeStatus) {
        this.remainTime = timeStatus.getTime();
    }


    public Long getRemainTime() {
        Long between = getTimeWhenTimerStarted();
        if (between != null) return between;
        return remainTime;
    }

    private Long getTimeWhenTimerStarted() {
        if (Objects.nonNull(endTime)) {
            Duration between = Duration.between(LocalDateTime.now(), endTime);
            return between.isNegative() ? 0 : between.toMinutes();
        }
        return null;
    }
}