package com.eventtickets.logictier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private Long buyerId;
    private Long eventId;
    private String ticketNr;
    private double price;
    private int nrOfTickets;

}
