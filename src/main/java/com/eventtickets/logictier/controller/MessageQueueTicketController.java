package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.TicketService;
import com.eventtickets.logictier.service.dto.BookTicketDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueTicketController
	extends AbstractMessageQueueController {

	@NonNull
	private TicketService service;

	@RabbitListener(queues = "bookTicket")
	public void bookTicket(Message request) {
		try {

			BookTicketDTO t = deserialize(request.getBody(),
				BookTicketDTO.class);

			succeed(request, service.bookTickets(t));
		}

		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@RabbitListener(queues = "getTicketsForUser")
	public void getTicketsForUser(Message request) {
		try {
			long userId = deserialize(request.getBody(), Long.class);
			succeed(request, service.getTicketsForUser(userId));
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
