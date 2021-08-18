package com.example.ddd.pomodoro.domain;

public enum TimeStatus {
    STOP(0), WORK_SESSION(25), BREAKS(5);

    private long time;

    TimeStatus(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
