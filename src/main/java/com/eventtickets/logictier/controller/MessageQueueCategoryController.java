package com.eventtickets.logictier.controller;

import com.eventtickets.logictier.model.Category;
import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueCategoryController
	extends AbstractMessageQueueController {
	private CategoryService categoryService;

	public MessageQueueCategoryController(
		@NonNull RabbitTemplate rabbit,
		@NonNull ObjectMapper jsonSerializer,
		@Value("${eventTicket.mq.dlx-name}") String dlx,
		CategoryService categoryService) {
		super(rabbit, jsonSerializer, dlx);
		this.categoryService = categoryService;
	}

	@RabbitListener(queues = "getAllCategories")
	public void getAllCategories(Message request) {
		try {
			succeed(request, serialize(categoryService.getAllCategories()));
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@RabbitListener(queues = "createCategory")
	public void createCategory(Message request) {
		try {
			Category category = deserialize(request.getBody(), Category.class);

			succeed(request,
				serialize(categoryService.createCategory(category)));

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
