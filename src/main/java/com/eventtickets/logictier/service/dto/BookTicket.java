package com.eventtickets.logictier.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTicket {
    private long buyerId;
    private long eventId;
    private int nrTickets;
    private double price;


}
