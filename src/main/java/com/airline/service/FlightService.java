package com.airline.service;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airline.entity.Flight;

import com.airline.entity.Schedule;
import com.airline.repository.FlightRepository;
import com.airline.repository.ScheduleRepository;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Flight> getAllFlights(String sort) {
        List<Flight> flights = flightRepository.findAll();
        if ("asc".equalsIgnoreCase(sort)) {
            flights.sort(Comparator.comparing(Flight::getFlightNumber));
        }
        return flights;
    }

    public Flight getFlight(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }

    public List<Schedule> getFlightSchedules(Long flightId, String dates) {
        flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));
        return scheduleRepository.findByFlightId(flightId);
    }
}