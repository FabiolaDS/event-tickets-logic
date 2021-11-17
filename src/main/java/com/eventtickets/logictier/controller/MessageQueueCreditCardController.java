package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.service.CreditCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueCreditCardController {
    @NonNull
    private CreditCardService cservice;
    @NonNull
    private ObjectMapper jsonSerializer;

    @RabbitListener(queues = "addCreditCard")
    public String addCreditCard(byte[] bytes) {
        try {
            String json = new String(bytes);

            System.out.println("RECEIVED " + json);

            CreditCard c = jsonSerializer.readValue(json, CreditCard.class);

            return jsonSerializer.writeValueAsString(cservice.addCreditCard(c));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "getCreditCards")
    public String getCreditCardsForUser(byte[] bytes) {
        long userId = Long.parseLong(new String(bytes));
        try {
            return jsonSerializer.writeValueAsString(cservice.getCreditCardsForUser(userId));
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }
    }
}


