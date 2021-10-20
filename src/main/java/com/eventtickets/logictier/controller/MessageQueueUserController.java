package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.service.UserService;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor public class MessageQueueUserController
{
  @NonNull private RabbitTemplate rabbitTemplate;
  @NonNull private UserService service;
  @NonNull private ObjectMapper jsonSerializer;

  @RabbitListener(queues = "registerUser") public String registerUser(
      byte[] bytes)
  {

    try
    {
      String json = new String(bytes);
      RegisterUserDto userDto = jsonSerializer
          .readValue(json, RegisterUserDto.class);
      User userCreated = service.registerUser(userDto);
      return jsonSerializer.writeValueAsString(userCreated);
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
  }

  @RabbitListener(queues = "loginUser", ackMode = "AUTO") public String loginUser(byte[] bytes)
  {
    try
    {
      String json = new String(bytes);
      LoginUserDTO loginUserDTO = jsonSerializer
          .readValue(json, LoginUserDTO.class);
      User user = service.login(loginUserDTO);
      return jsonSerializer.writeValueAsString(user);
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
    catch (IllegalArgumentException e)
    {
      return e.getMessage();
    }

  }
}
