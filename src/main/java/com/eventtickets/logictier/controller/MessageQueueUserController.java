package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.service.UserService;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueUserController extends AbstractMessageQueueController {

	@NonNull
	private UserService service;

	@RabbitListener(queues = "registerUser")
	public void registerUser(Message request) {

		try {
			RegisterUserDTO
				userDto = deserialize(request.getBody(),
					RegisterUserDTO.class);
			User userCreated = service.registerUser(userDto);

			succeed(request, userCreated);

		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalArgumentException e) {

			fail(request, e.getMessage().getBytes());
		}
	}

	@RabbitListener(queues = "loginUser", ackMode = "AUTO")
	public void loginUser(Message request) {
		try {
			LoginUserDTO loginUserDTO = deserialize(request.getBody(),
				LoginUserDTO.class);

			User user = service.login(loginUserDTO);

			succeed(request, serialize(user));

		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalArgumentException e) {
			try {
				fail(request, serialize(e.getMessage()));
			}
			catch (JsonProcessingException ex) {
				throw new RuntimeException(ex);
			}
		}

	}

	@RabbitListener(queues = "updateUser")
	public void updateUser(Message request) {
		try {

			User updateUser = deserialize(request.getBody(), User.class);

			updateUser = service.updateUserData(updateUser);

			succeed(request, serialize(updateUser));
		}
		catch (JsonMappingException e) {
			throw new RuntimeException(e);
		}
		catch (JsonProcessingException e) {
			try {
				fail(request, serialize(e.getMessage()));
			}
			catch (JsonProcessingException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
