package com.airline.repository;

import com.airline.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFlightId(Long flightId);

    List<Schedule> findByFlightIdAndDepartureTimeBetween(Long flightId, LocalDateTime start, LocalDateTime end);
}