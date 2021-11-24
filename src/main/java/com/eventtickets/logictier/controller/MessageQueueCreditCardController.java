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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageQueueCreditCardController
	extends AbstractMessageQueueController {
	@NonNull
	private CreditCardService cservice;

	@RabbitListener(queues = "addCreditCard")
	public void addCreditCard(Message request) {
		try {
			CreateCardDTO c = deserialize(request.getBody(),
				CreateCardDTO.class);

			succeed(request, cservice.addCreditCard(c));

		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@RabbitListener(queues = "getCreditCards")
	public void getCreditCardsForUser(Message request) {

		try {
			long userId = deserialize(request.getBody(), Long.class);
			succeed(request, cservice.getCreditCardsForUser(userId));
		}
		catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}
}


