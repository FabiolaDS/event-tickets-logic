package com.eventtickets.logictier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	private String ticketNr;
	private long eventId;
	private long paymentId;
	private long buyerId;
	private LocalDateTime timeOfPurchase;
}
