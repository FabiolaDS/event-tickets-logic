package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.service.EventService;
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
public class MessageQueueEventController
	extends AbstractMessageQueueController {

	@NonNull
	private EventService service;

	public MessageQueueEventController(
		@NonNull RabbitTemplate rabbit,
		@NonNull ObjectMapper jsonSerializer,
		@Value("${eventTicket.mq.dlx-name}") String dlx, EventService service) {
		super(rabbit, jsonSerializer, dlx);
		this.service = service;
	}

	@RabbitListener(queues = "getUpcomingEvents")
	public void getUpcomingEvents(Message request) {
		try {
			succeed(request, serialize(service.findUpcomingEvents()));
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@RabbitListener(queues = "addEvent")
	public void addEvent(Message request) {
		try {
			Event e = deserialize(request.getBody(), Event.class);

			succeed(request, serialize(service.addEvent(e)));

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

	@RabbitListener(queues = "getEventById")
	public void getEventById(Message request) {

		try {
			long eventId = deserialize(request.getBody(), Long.class);
			succeed(request, serialize(service.getById(eventId)));
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

	@RabbitListener(queues = "updateEvent")
	public void updateEvent(Message request) {
		try {
			Event event = deserialize(request.getBody(), Event.class);
			succeed(request, serialize(service.updateEvent(event)));

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

	@RabbitListener(queues = "cancelEvent")
	public void cancelEvent(Message request) {
		try {
			long eventId = deserialize(request.getBody(), Long.class);
			succeed(request, serialize(service.cancelEvent(eventId)));
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

	@RabbitListener(queues = "getUpcomingEventsByCategory")
	public void getUpcomingEventsByCategory(Message request) {
		try {
			long categoryId = deserialize(request.getBody(), Long.class);
			succeed(request,
				serialize(service.findUpcomingEventsByCategory(categoryId)));
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

	@RabbitListener(queues = "getUpcomingEventsByLocation")
	public void getUpcomingEventsByLocation(Message request) {
		try {
			String location = deserialize(request.getBody(), String.class);
			succeed(request,
				serialize(service.findUpcomingEventsByLocation(location)));
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
