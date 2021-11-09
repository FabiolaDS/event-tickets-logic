package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.network.TicketRepository;
import com.eventtickets.logictier.service.dto.BookTicket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket bookTicket(BookTicket bookTicket) {
        String ticketNr = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(bookTicket.getBuyerId(),
                bookTicket.getEventId(),
                ticketNr,
                bookTicket.getPrice(),
                bookTicket.getNrTickets()
        );
        return ticketRepository.createTicket(ticket);

    }

    @Override
    public List<Ticket> getTicketsForUser(Long id) {
        return ticketRepository.getByUserId(id);
    }
}

