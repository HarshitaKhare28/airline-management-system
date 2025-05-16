package com.airline.service;

import com.airline.entity.Schedule;
import com.airline.entity.Ticket;
import com.airline.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ScheduleService scheduleService;

    public TicketService(TicketRepository ticketRepository, ScheduleService scheduleService) {
        this.ticketRepository = ticketRepository;
        this.scheduleService = scheduleService;
    }

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getPassengerName() == null || ticket.getPassengerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }
        Schedule schedule = scheduleService.getScheduleById(ticket.getSchedule().getId());
        ticket.setSchedule(schedule);
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }
}