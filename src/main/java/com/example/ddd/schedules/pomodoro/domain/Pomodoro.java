package com.example.ddd.schedules.pomodoro.domain;

import com.example.ddd.schedules.schedule.domain.Schedule;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Enumerated(EnumType.STRING)
    private TimeStatus timeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    protected Pomodoro() {
    }

    public Pomodoro(String todo) {
        this.todo = new Todo(todo);
        this.timeStatus = TimeStatus.STOP;
        this.remainTime = new RemainTime(TimeStatus.STOP);
    }

    public void start(TimeStatus timeStatus) {
        this.timeStatus = timeStatus;
        this.remainTime = new RemainTime(timeStatus);
        setTimer(timeStatus);
    }

    public static Pomodoro of(String toto) {
        return new Pomodoro(toto);
    }

    private void setTimer(TimeStatus timeStatus) {
        Timer timer = new Timer();
        timer.schedule(this, getEndTime(timeStatus));
    }

    private Date getEndTime(TimeStatus timeStatus) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(timeStatus.getTime());
        return Date.from(localDateTime.atZone(
                ZoneId.systemDefault()).toInstant());
    }

    public String getTodo() {
        return todo.getTodo();
    }

    @Override
    public void run() {
        this.timeStatus = TimeStatus.STOP;
    }

    public Long getRemainTime() {
        return remainTime.getRemainTime();
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
