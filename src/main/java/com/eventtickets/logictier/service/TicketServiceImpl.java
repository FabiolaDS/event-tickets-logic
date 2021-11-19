package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.network.TicketRepository;
import com.eventtickets.logictier.service.dto.BookTicket;
import com.eventtickets.logictier.service.dto.TicketWithEventDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    @NonNull
    private TicketRepository ticketRepository;
    @NonNull
    private EventService eventService;

    @Override
    public Ticket bookTicket(BookTicket bookTicket) {
        String ticketNr = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(bookTicket.getBuyerId(),
                bookTicket.getEventId(),
                ticketNr,
                bookTicket.getNrOfTickets()
        );

        System.out.println("CREATED TICKET " + ticket);

        return ticketRepository.createTicket(ticket);
    }

    @Override
    public List<TicketWithEventDto> getTicketsForUser(Long id) {
        return ticketRepository.getByUserId(id)
                .stream()
                .map(t -> {
                    Event e = eventService.getById(t.getEventId());
                    return new TicketWithEventDto(
                            e.getId(),
                            t.getTicketNr(),
                            e.getName(),
                            e.getDateTime(),
                            e.getLocation(),
                            e.getPrice() * t.getNrOfTickets(),
                            t.getNrOfTickets(),
                            e.getThumbnail());

                })
                .collect(Collectors.toList());
    }
}

