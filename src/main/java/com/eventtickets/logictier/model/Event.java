package com.eventtickets.logictier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	private Long id;
	@NotBlank(message = "The event name cannot be empty")
	private String name;

	@NotBlank(message = "The description cannot be empty")
	private String description;

	@NotBlank(message = "The location cannot be empty")
	private String location;

	@NotBlank(message = "The image cannot be empty")
	@URL(message = "Please specify a valid url")
	private String thumbnail;

	@Positive(message = "There has to be at least 1 available ticket")
	private int availableTickets;

	private boolean isCancelled;

	@Future(message = "The date of the event has to be in the future")
	private LocalDateTime timeOfTheEvent;

	@PositiveOrZero(message = "The price cannot be negative")
	private double ticketPrice;
	private long organizerId;
	private int bookedTickets;
}
