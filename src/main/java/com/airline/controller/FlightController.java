package com.airline.controller;

import com.airline.entity.Flight;
import com.airline.entity.Schedule;
import com.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(
            @RequestParam(defaultValue = "asc") String sort) {
        return ResponseEntity.ok(flightService.getAllFlights(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlight(id));
    }

    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<Schedule>> getFlightSchedules(
            @PathVariable Long id,
            @RequestParam(required = false) String dates) {
        return ResponseEntity.ok(flightService.getFlightSchedules(id, dates));
    }
}