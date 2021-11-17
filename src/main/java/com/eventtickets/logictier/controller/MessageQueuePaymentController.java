package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.PaymentService;
import com.eventtickets.logictier.service.dto.MakePaymentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageQueuePaymentController {

    @NonNull
    private PaymentService paymentService;
    @NonNull
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "makePayment")
    public String makePayment(byte[] bytes) {
        try {
            String json = new String(bytes);
            MakePaymentDto makePaymentDto = objectMapper.readValue(json, MakePaymentDto.class);
            return objectMapper.writeValueAsString(paymentService.makePayment(makePaymentDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
}
