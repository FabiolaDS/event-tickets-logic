package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueEventController {

    @NonNull
    private EventService service;
    @NonNull
    private ObjectMapper jsonSerializer;

    @RabbitListener(queues = "getAllEvents")
    public String getAllEvents() {
        try {
            return jsonSerializer.writeValueAsString(service.getAllEvents());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "addEvent")
    public String addEvent(byte[] bytes) {
        try {
            String json = new String(bytes);
            Event e = jsonSerializer.readValue(json, Event.class);

            return jsonSerializer.writeValueAsString(service.addEvent(e));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "getEventById")
    public String getEventById(byte[] bytes) {
        long eventId = Long.parseLong(new String(bytes));
        try {
            return jsonSerializer.writeValueAsString(service.getById(eventId));
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }
}
