package com.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.TicketRequest;
import com.airline.entity.Schedule;
import com.airline.entity.Ticket;
import com.airline.repository.ScheduleRepository;
import com.airline.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Ticket createTicket(TicketRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + request.getScheduleId()));

        Ticket ticket = new Ticket();
        ticket.setPassengerName(request.getPassengerName());
        ticket.setSchedule(schedule);
        ticket.setStatus("BOOKED");

        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    public void deleteTicket(Long id) {
        Ticket ticket = getTicket(id);
        if ("CANCELLED".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket already cancelled: " + id);
        }
        ticket.setStatus("CANCELLED");
        ticketRepository.save(ticket);
    }
}