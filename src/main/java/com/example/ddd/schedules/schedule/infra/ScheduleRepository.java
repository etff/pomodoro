package com.example.ddd.schedules.schedule.infra;

import com.example.ddd.schedules.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
