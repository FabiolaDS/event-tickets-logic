package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.service.CreditCardService;
import com.eventtickets.logictier.service.dto.CreateCardDTO;
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
public class MessageQueueCreditCardController
        extends AbstractMessageQueueController {
    @NonNull
    private CreditCardService cservice;

    public MessageQueueCreditCardController(
            @NonNull RabbitTemplate rabbit,
            @NonNull ObjectMapper jsonSerializer,
            @Value("${eventTicket.mq.dlx-name}") String dlx,
            CreditCardService service) {

        super(rabbit, jsonSerializer, dlx);
        this.cservice = service;
    }

    @RabbitListener(queues = "addCreditCard")
    public void addCreditCard(Message request) {
        try {
            CreateCardDTO c = deserialize(request.getBody(),
                    CreateCardDTO.class);

            succeed(request, serialize(cservice.addCreditCard(c)));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            try {
                fail(request, serialize(e.getMessage()));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @RabbitListener(queues = "getCreditCards")
    public void getCreditCardsForUser(Message request) {
        try {
            long userId = deserialize(request.getBody(), Long.class);
            succeed(request, serialize(cservice.getCreditCardsForUser(userId)));
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            try {
                fail(request, serialize(e.getMessage()));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}


