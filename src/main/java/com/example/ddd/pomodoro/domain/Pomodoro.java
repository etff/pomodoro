package com.example.ddd.pomodoro.domain;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Getter
@Entity
public class Pomodoro extends TimerTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Todo todo;

    @Embedded
    private RemainTime remainTime;

    public Long getRemainTime() {
        return remainTime.getRemainTime();
    }

    @Enumerated(EnumType.STRING)
    private TimeStatus timeStatus;

    protected Pomodoro() {
    }

    public Pomodoro(String todo) {
        this.todo = new Todo(todo);
        this.timeStatus = TimeStatus.STOP;
        this.remainTime = new RemainTime(TimeStatus.STOP);
    }

    public String getTodo() {
        return todo.getTodo();
    }

    @Override
    public void run() {
        this.timeStatus = TimeStatus.STOP;
    }

    public void start(TimeStatus timeStatus) {
        this.timeStatus = timeStatus;
        this.remainTime = new RemainTime(timeStatus);
        setTimer(timeStatus);
    }

    private void setTimer(TimeStatus timeStatus) {
        Timer timer = new Timer();
        timer.schedule(this, getEndDate(timeStatus));
    }

    private Date getEndDate(TimeStatus timeStatus) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(timeStatus.getTime());
        return Date.from(localDateTime.atZone(
                ZoneId.systemDefault()).toInstant());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pomodoro)) return false;
        Pomodoro pomodoro = (Pomodoro) o;
        return Objects.equals(getId(), pomodoro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
