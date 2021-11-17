package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.service.dto.BookTicket;
import com.eventtickets.logictier.service.dto.TicketWithEventDto;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(BookTicket bookTicket);
    List<TicketWithEventDto> getTicketsForUser(Long id);
}
