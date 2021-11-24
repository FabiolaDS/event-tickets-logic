package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.TicketService;
import com.eventtickets.logictier.service.dto.BookTicketDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueTicketController
	extends AbstractMessageQueueController {

	@NonNull
	private TicketService service;

	public MessageQueueTicketController(
		@NonNull RabbitTemplate rabbit,
		@NonNull ObjectMapper jsonSerializer,
		@Value("${eventTicket.mq.dlx-name}") String dlx,
		TicketService service) {
		super(rabbit, jsonSerializer, dlx);
		this.service = service;
	}

	@RabbitListener(queues = "bookTicket")
	public void bookTicket(Message request) {
		try {

			BookTicketDTO t = deserialize(request.getBody(),
				BookTicketDTO.class);

			succeed(request, serialize(service.bookTickets(t)));
		}

		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		catch (IllegalArgumentException e) {
			try {
				fail(request, serialize(e.getMessage()));
			}
			catch (JsonProcessingException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@RabbitListener(queues = "getTicketsForUser")
	public void getTicketsForUser(Message request) {
		try {
			long userId = deserialize(request.getBody(), Long.class);
			succeed(request, serialize(service.getTicketsForUser(userId)));
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		catch (IllegalArgumentException e) {
			try {
				fail(request, serialize(e.getMessage()));
			}
			catch (JsonProcessingException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
