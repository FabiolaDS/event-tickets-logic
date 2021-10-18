package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Bean
    public Queue testQueue() {
        return new Queue("eventTicketLogicQueue");
    }
//
//    @Bean
//    public MessageQueueEventController mqEventController(RabbitTemplate t, EventService s, ObjectMapper om) {
//        return new MessageQueueEventController(t, s, om);
//    }
}
