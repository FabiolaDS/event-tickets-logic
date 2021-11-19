package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.PaymentService;
import com.eventtickets.logictier.service.dto.FindTicketDTO;
import com.eventtickets.logictier.service.dto.MakePaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

            System.out.println(json);

            MakePaymentDTO makePaymentDto = objectMapper.readValue(json, MakePaymentDTO.class);
            return objectMapper.writeValueAsString(paymentService.makePayment(makePaymentDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
    @RabbitListener(queues = "findPaymentForTicket")
    public  String findPaymentForTicket(byte[] bytes)
    {
        try{
            String json= new String(bytes);
             FindTicketDTO findTicketDto = objectMapper.readValue(json, FindTicketDTO.class);
            return objectMapper.writeValueAsString(paymentService.findForTicket(findTicketDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
}
