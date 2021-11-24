package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueEventController
	extends AbstractMessageQueueController {

	@NonNull
	private EventService service;

	@RabbitListener(queues = "getUpcomingEvents")
	public void getUpcomingEvents(Message request) {
		succeed(request, service.findUpcomingEvents());
	}

	@RabbitListener(queues = "addEvent")
	public void addEvent(Message request) {
		try {
			Event e = deserialize(request.getBody(), Event.class);

			succeed(request, service.addEvent(e));

		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@RabbitListener(queues = "getEventById")
	public void getEventById(Message request) {

		try {
			long eventId = deserialize(request.getBody(), Long.class);
			succeed(request, service.getById(eventId));
		}
		catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}
}
