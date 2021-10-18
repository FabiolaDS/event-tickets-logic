package com.eventtickets.logictier.controller;

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
    private RabbitTemplate rabbitTemplate;
    @NonNull
    private EventService service;
    @NonNull
    private ObjectMapper jsonSerializer;

    @RabbitListener(queues = "eventTicketLogicQueue")
    public String receiveMessage(byte[] message) throws JsonProcessingException {
        String req = new String(message);

        switch (req) {
            case "getAllEvents":
                return jsonSerializer.writeValueAsString(service.getAllEvents());

            default:
                throw new IllegalArgumentException();
        }
    }
}
