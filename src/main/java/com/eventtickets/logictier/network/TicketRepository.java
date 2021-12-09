package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket createTicket(Ticket ticket);
    List<Ticket> getByUserId(long id);

}
