package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.Notification;
import com.eventtickets.logictier.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueNotificationController
	extends AbstractMessageQueueController {

	private NotificationService notificationService;

	public MessageQueueNotificationController(
		@NonNull RabbitTemplate rabbit,
		@NonNull ObjectMapper jsonSerializer,
		@Value("${eventTicket.mq.dlx-name}") String dlx,
		NotificationService notificationService) {
		super(rabbit, jsonSerializer, dlx);
		this.notificationService = notificationService;
	}

	@RabbitListener(queues = "createNotification")
	public void createNotification(Message request) {
		try {
			Notification notification = deserialize(request.getBody(),
				Notification.class);
			succeed(request, serialize(
				notificationService.createNotification(notification)));
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

	@RabbitListener(queues = "getNotificationsByUser")
	public void getNotificationsByUser(Message request) {
		try {
			long userId = deserialize(request.getBody(), Long.class);
			succeed(request,
				serialize(notificationService.getNotificationsByUser(userId)));
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
}
