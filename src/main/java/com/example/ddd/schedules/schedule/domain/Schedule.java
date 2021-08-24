package com.example.ddd.schedules.schedule.domain;

import com.example.ddd.schedules.pomodoro.domain.Pomodoro;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Pomodoro> pomodoros = new ArrayList<>();

    protected Schedule() {
    }

    protected Schedule(Long userId, List<Pomodoro> pomodoros) {
        this.userId = userId;
        this.pomodoros = pomodoros;
    }

    public static Schedule of(Long userId, List<Pomodoro> pomodoros) {
        return new Schedule(userId, pomodoros);
    }
}
