package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.service.dto.BookTicketDTO;
import com.eventtickets.logictier.service.dto.TicketGroupDTO;
import com.eventtickets.logictier.service.dto.TicketWithEventDTO;

import java.util.List;

public interface TicketService {
    List<Ticket> bookTickets(BookTicketDTO bookTicket);
    List<TicketGroupDTO> getTicketsForUser(Long id);
}
