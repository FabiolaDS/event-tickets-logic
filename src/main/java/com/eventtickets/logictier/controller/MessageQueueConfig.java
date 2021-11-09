package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Bean
    public Queue getAllEventsRequestQueue() {
        return new Queue("getAllEvents");
    }

    @Bean
    public Queue addEventQueue() {
        return new Queue("addEvent");
    }

    @Bean
    public Queue registerUserQueue() {
        return new Queue("registerUser");
    }

    @Bean
    public Queue loginUserQueue() {
        return new Queue("loginUser");
    }

    @Bean
    public Queue updateUserQueue() {
        return new Queue("updateUser");
    }

    @Bean
    public Queue bookTicketQueue() {
        return new Queue("bookTicket");


    }

    @Bean
    public Queue getTicketsForUserQueue() {
        return new Queue("getTicketsForUser");
    }

    @Bean
    public Queue getEventByIdQueue()
    {
        return new Queue("getEventById");

    }



}
