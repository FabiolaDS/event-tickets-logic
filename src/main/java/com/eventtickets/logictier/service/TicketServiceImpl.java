package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.network.TicketRepository;
import com.eventtickets.logictier.service.dto.BookTicketDTO;
import com.eventtickets.logictier.service.dto.TicketGroupDTO;
import com.eventtickets.logictier.service.dto.TicketWithEventDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

	@NonNull
	private TicketRepository ticketRepository;
	@NonNull
	private EventService eventService;
	@NonNull
	private Validator validator;

	@Override
	public List<Ticket> bookTickets(BookTicketDTO bookTicket) {
		Set<ConstraintViolation<BookTicketDTO>> violations = validator
			.validate(bookTicket);
		for (ConstraintViolation<BookTicketDTO> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}

		Event event = eventService.getById(bookTicket.getEventId());
		if (bookTicket.getNoOfTickets() > event.getAvailableTickets() - event
			.getBookedTickets()) {
			throw new IllegalArgumentException("Not enough remaining tickets");
		}

		if (event.getIsCancelled()) {
			throw new IllegalArgumentException("The event was cancelled");
		}

		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < bookTicket.getNoOfTickets(); i++) {

			String ticketNr = UUID.randomUUID().toString();
			Ticket ticket = new Ticket(ticketNr,
				bookTicket.getEventId(),
				bookTicket.getPaymentId(), bookTicket.getBuyerId());

			ticket = ticketRepository
				.createTicket(bookTicket.getBuyerId(), ticket);
			tickets.add(ticket);
		}

		return tickets;
	}

	@Override
	public List<TicketGroupDTO> getTicketsForUser(Long id) {
		// group tickets by event id
		Map<Long, List<Ticket>> ticketGroups = ticketRepository.getByUserId(id)
			.stream()
			.collect(Collectors.groupingBy(Ticket::getEventId));

		return ticketGroups.entrySet()
			.stream()
			.map(e -> {
				Event event = eventService.getById(e.getKey());

				return new TicketGroupDTO(event.getId(),
					event.getName(),
					event.getTimeOfTheEvent(),
					event.getThumbnail(),
					event.getLocation(),
					event.getTicketPrice(),

					e.getValue());
			})
			.collect(Collectors.toList());
	}
}

