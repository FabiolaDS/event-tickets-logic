package com.eventtickets.logictier.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public abstract class AbstractMessageQueueController {
	@NonNull
	private RabbitTemplate rabbit;

	@NonNull
	private ObjectMapper jsonSerializer;

	@NonNull
	private String dlx;

	protected void respond(String exchange, String routingKey, Message request,
		byte[] response) {
		MessageProperties props = MessagePropertiesBuilder
			.newInstance()
			.setCorrelationId(
				request.getMessageProperties().getCorrelationId())
			.build();

		Message responseMsg = MessageBuilder
			.withBody(response)
			.andProperties(props)
			.build();

		rabbit.send(exchange, routingKey, responseMsg);

		System.out.format("SENT %s TO %s/%s\n",
			new String(response),
			exchange, routingKey);
	}

	protected void succeed(Message request, byte[] response) {
		respond("",
			request.getMessageProperties().getReplyTo(),
			request,
			response);
	}

	protected void fail(Message request, byte[] response) {
		respond(dlx, "", request, response);
	}

	protected <T> T deserialize(byte[] arr, Class<T> target)
		throws JsonProcessingException {
		String json = new String(arr);
		return jsonSerializer.readValue(json, target);
	}

	protected byte[] serialize(Object obj) throws JsonProcessingException {
		return jsonSerializer.writeValueAsString(obj).getBytes();
	}
}
