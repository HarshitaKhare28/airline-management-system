
package com.airline.controller;

import com.airline.entity.Flight;
import com.airline.entity.Schedule;
import com.airline.service.FlightService;
import com.airline.service.ScheduleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final ScheduleService scheduleService;

    public FlightController(FlightService flightService, ScheduleService scheduleService) {
        this.flightService = flightService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(flightService.getAllFlights(sort));
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<Schedule>> getSchedulesByFlightId(
            @PathVariable Long id,
            @RequestParam(required = false) String dates) {
        return ResponseEntity.ok(scheduleService.getSchedulesByFlightId(id, dates));
    }
}