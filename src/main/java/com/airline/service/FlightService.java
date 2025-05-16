package com.airline.service;

import com.airline.entity.Flight;
import com.airline.repository.FlightRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        Optional<Flight> existingFlight = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if (existingFlight.isPresent()) {
            throw new IllegalArgumentException("Flight number already exists");
        }
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights(String sort) {
        List<Flight> flights = flightRepository.findAll();
        if ("asc".equalsIgnoreCase(sort)) {
            flights.sort((f1, f2) -> f1.getFlightNumber().compareTo(f2.getFlightNumber()));
        } else if ("desc".equalsIgnoreCase(sort)) {
            flights.sort((f1, f2) -> f2.getFlightNumber().compareTo(f1.getFlightNumber()));
        }
        return flights;
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
    }
}