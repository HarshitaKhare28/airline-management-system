package com.airline.service;

import org.springframework.stereotype.Service;

import com.airline.entity.Flight;
import com.airline.entity.Schedule;
import com.airline.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final FlightService flightService;

    public ScheduleService(ScheduleRepository scheduleRepository, FlightService flightService) {
        this.scheduleRepository = scheduleRepository;
        this.flightService = flightService;
    }

    public Schedule createSchedule(Schedule schedule) {
        if (schedule.getDepartureTime().isAfter(schedule.getArrivalTime())) {
            throw new IllegalArgumentException("Departure time must be before arrival time");
        }
        if (schedule.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesByFlightId(Long flightId, String dates) {
        Flight flight = flightService.getFlightById(flightId);
        if (dates == null || dates.isEmpty()) {
            return flight.getSchedules();
        }
        String[] dateRange = dates.split(",");
        if (dateRange.length != 2) {
            throw new IllegalArgumentException("Dates must be in format 'startDate,endDate'");
        }
        LocalDateTime start = LocalDateTime.parse(dateRange[0], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(dateRange[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return scheduleRepository.findByFlightIdAndDepartureTimeBetween(flightId, start, end);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    }
}
