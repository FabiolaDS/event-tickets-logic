package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.TicketService;
import com.eventtickets.logictier.service.dto.BookTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Component
@RequiredArgsConstructor
public class MessageQueueTicketController {
    @NonNull
    private RabbitTemplate rabbitTemplate;
    @NonNull private TicketService service;
    @NonNull private ObjectMapper jsonSerializer;

    @RabbitListener(queues = "bookTicket")
    public  String bookTicket(byte[] bytes)
    {
        try
        {
            String json = new String(bytes);
            BookTicket t = jsonSerializer.readValue(json, BookTicket.class);

            return jsonSerializer.writeValueAsString(service.bookTicket(t));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues = "getTicketsForUser")
    public String getTicketsForUser(byte[]bytes)
    {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(bytes);
        buffer.flip();

        long userId = buffer.getLong();
        try {
            return jsonSerializer.writeValueAsString(service.getTicketsForUser(userId));
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }


    }
}
