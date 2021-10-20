package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class MessageQueueConfig
{

  @Bean public Queue getAllEventsRequestQueue()
  {
    return new Queue("getAllEvents");
  }

  @Bean public Queue addEventQueue()
  {
    return new Queue("addEvent");
  }

  @Bean public Queue registerUserQueue()
  {
    return new Queue("registerUser");
  }

  @Bean public Queue loginUserQueue()
  {
    return new Queue("loginUser");
  }
}
