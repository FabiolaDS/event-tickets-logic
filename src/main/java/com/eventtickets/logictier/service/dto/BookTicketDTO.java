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
    private long buyerId;
    @Positive
    private long eventId;
    @Positive(message = "You have to book at least 1 ticket")
    private int nrOfTickets;
}
