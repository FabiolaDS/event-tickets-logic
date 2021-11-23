package com.eventtickets.logictier.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketDTO
{
    @Positive
    private long eventId;
    private long paymentId;
    private long buyerId;
    private int noOfTickets;
}
