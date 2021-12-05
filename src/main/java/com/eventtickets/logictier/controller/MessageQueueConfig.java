package com.eventtickets.logictier.controller;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

	@Bean
	public DirectExchange logicTierExchange(
		@Value("${eventTicket.mq.exchange-name}") String exchangeName) {
		return new DirectExchange(exchangeName);
	}

	@Bean
	public FanoutExchange deadLetterExchange(
		@Value("${eventTicket.mq.dlx-name}") String dlx) {
		return new FanoutExchange(dlx);
	}

	@Bean
	public Queue getUpcomingEventsQueue() {
		return new Queue("getUpcomingEvents");
	}

	@Bean
	public Binding getUpcomingEventsBinding(DirectExchange logicTierExchange,
		Queue getUpcomingEventsQueue) {
		return BindingBuilder
			.bind(getUpcomingEventsQueue)
			.to(logicTierExchange)
			.with("getUpcomingEvents");
	}

	@Bean
	public Queue addEventQueue() {
		return new Queue("addEvent");
	}

	@Bean
	public Binding addEventBinding(DirectExchange logicTierExchange,
		Queue addEventQueue) {
		return BindingBuilder
			.bind(addEventQueue)
			.to(logicTierExchange)
			.with("addEvent");
	}

	@Bean
	public Queue registerUserQueue() {
		return new Queue("registerUser");
	}

	@Bean
	public Binding registerUserBinding(DirectExchange logicTierExchange,
		Queue registerUserQueue) {
		return BindingBuilder
			.bind(registerUserQueue)
			.to(logicTierExchange)
			.with("registerUser");
	}

	@Bean
	public Queue loginUserQueue() {
		return new Queue("loginUser");
	}

	@Bean
	public Binding loginUserBinding(DirectExchange logicTierExchange,
		Queue loginUserQueue) {
		return BindingBuilder
			.bind(loginUserQueue)
			.to(logicTierExchange)
			.with("loginUser");
	}

	@Bean
	public Queue updateUserQueue() {
		return new Queue("updateUser");
	}

	@Bean
	public Binding updateUserBinding(DirectExchange logicTierExchange,
		Queue updateUserQueue) {
		return BindingBuilder
			.bind(updateUserQueue)
			.to(logicTierExchange)
			.with("updateUser");
	}

	@Bean
	public Queue bookTicketQueue() {
		return new Queue("bookTicket");
	}

	@Bean
	public Binding bookTicketBinding(DirectExchange logicTierExchange,
		Queue bookTicketQueue) {
		return BindingBuilder
			.bind(bookTicketQueue)
			.to(logicTierExchange)
			.with("bookTicket");
	}

	@Bean
	public Queue getTicketsForUserQueue() {
		return new Queue("getTicketsForUser");
	}

	@Bean
	public Binding getTicketsForUserBinding(DirectExchange logicTierExchange,
		Queue getTicketsForUserQueue) {
		return BindingBuilder
			.bind(getTicketsForUserQueue)
			.to(logicTierExchange)
			.with("getTicketsForUser");
	}

	@Bean
	public Queue getEventByIdQueue() {
		return new Queue("getEventById");
	}

	@Bean
	public Binding getEventByIdBinding(DirectExchange logicTierExchange,
		Queue getEventByIdQueue) {
		return BindingBuilder
			.bind(getEventByIdQueue)
			.to(logicTierExchange)
			.with("getEventById");
	}

	@Bean
	public Queue addCreditCardQueue() {
		return new Queue("addCreditCard");
	}

	@Bean
	public Binding addCreditCardBinding(DirectExchange logicTierExchange,
		Queue addCreditCardQueue) {
		return BindingBuilder
			.bind(addCreditCardQueue)
			.to(logicTierExchange)
			.with("addCreditCard");
	}

	@Bean
	public Queue getCreditCardsForUserQueue() {
		return new Queue("getCreditCards");
	}

	@Bean
	public Binding getCreditCardsBinding(DirectExchange logicTierExchange,
		Queue getCreditCardsForUserQueue) {
		return BindingBuilder
			.bind(getCreditCardsForUserQueue)
			.to(logicTierExchange)
			.with("getCreditCards");
	}

	@Bean
	public Queue findPaymentForTicketQueue() {
		return new Queue("findPaymentForTicket");
	}

	@Bean
	public Binding findPaymentForTicketBinding(DirectExchange logicTierExchange,
		Queue findPaymentForTicketQueue) {
		return BindingBuilder
			.bind(findPaymentForTicketQueue)
			.to(logicTierExchange)
			.with("findPaymentForTicket");
	}

	@Bean
	public Queue updateEventQueue() {
		return new Queue("updateEvent");
	}

	@Bean
	public Binding updateEventBinding(DirectExchange logicTierExchange,
		Queue updateEventQueue) {
		return BindingBuilder
			.bind(updateEventQueue)
			.to(logicTierExchange)
			.with("updateEvent");
	}

	@Bean
	public Queue cancelEventQueue() {
		return new Queue("cancelEvent");
	}

	@Bean
	public Binding cancelEventBinding(DirectExchange logicTierExchange,
		Queue cancelEventQueue) {
		return BindingBuilder
			.bind(cancelEventQueue)
			.to(logicTierExchange)
			.with("cancelEvent");
	}

	@Bean
	public Queue createCategoryQueue() {
		return new Queue("createCategory");
	}

	@Bean
	public Binding createCategoryBinding(DirectExchange logicTierExchange,
		Queue createCategoryQueue) {
		return BindingBuilder
			.bind(createCategoryQueue)
			.to(logicTierExchange)
			.with("createCategory");
	}

	@Bean
	public Queue getAllCategoriesQueue() {
		return new Queue("getAllCategories");
	}

	@Bean
	public Binding getAllCategoriesBinding(DirectExchange logicTierExchange,
		Queue getAllCategoriesQueue) {
		return BindingBuilder
			.bind(getAllCategoriesQueue)
			.to(logicTierExchange)
			.with("getAllCategories");
	}

	@Bean
	public Queue getUpcomingEventsByCategoryQueue() {
		return new Queue("getUpcomingEventsByCategory");
	}

	@Bean
	public Binding getUpcomingEventsByCategoryBinding(
		DirectExchange logicTierExchange,
		Queue getUpcomingEventsByCategoryQueue) {
		return BindingBuilder
			.bind(getUpcomingEventsByCategoryQueue)
			.to(logicTierExchange)
			.with("getUpcomingEventsByCategory");
	}

	@Bean
	public Queue createNotificationQueue() {
		return new Queue("createNotification");
	}

	@Bean
	public Binding createNotificationBinding(
		DirectExchange logicTierExchange,
		Queue createNotificationQueue) {
		return BindingBuilder
			.bind(createNotificationQueue)
			.to(logicTierExchange)
			.with("createNotification");
	}

	@Bean
	public Queue getNotificationsByUserQueue() {
		return new Queue("getNotificationsByUser");
	}

	@Bean
	public Binding getNotificationsByUserBinding(
		DirectExchange logicTierExchange,
		Queue getNotificationsByUserQueue) {
		return BindingBuilder
			.bind(getNotificationsByUserQueue)
			.to(logicTierExchange)
			.with("getNotificationsByUser");
	}

	@Bean
	public Queue grantAdminPrivilegeQueue() {
		return new Queue("grantAdminPrivilege");
	}

	@Bean
	public Binding grantAdminPrivilegeBinding(
		DirectExchange logicTierExchange,
		Queue grantAdminPrivilegeQueue) {
		return BindingBuilder
			.bind(grantAdminPrivilegeQueue)
			.to(logicTierExchange)
			.with("grantAdminPrivilege");
	}

	@Bean
	public Queue getAllUsersQueue() {
		return new Queue("getAllUsers");
	}

	@Bean
	public Binding getAllUsersBinding(
		DirectExchange logicTierExchange,
		Queue getAllUsersQueue) {
		return BindingBuilder
			.bind(getAllUsersQueue)
			.to(logicTierExchange)
			.with("getAllUsers");
	}

	@Bean
	public Queue getUpcomingEventsByLocationQueue() {
		return new Queue("getUpcomingEventsByLocation");
	}

	@Bean
	public Binding getUpcomingEventsByLocationBinding(
		DirectExchange logicTierExchange,
		Queue getUpcomingEventsByLocationQueue) {
		return BindingBuilder
			.bind(getUpcomingEventsByLocationQueue)
			.to(logicTierExchange)
			.with("getUpcomingEventsByLocation");
	}
}
