package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.service.dto.BookTicket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(BookTicket bookTicket);
    List<Ticket> getTicketsForUser(Long id);
}
