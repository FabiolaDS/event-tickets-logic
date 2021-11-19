package com.eventtickets.logictier.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class TicketWithEventDto {
//    private LocalDateTime ticketTimestamp;

    private Long eventId;
    private String ticketNumber ;
    private String nameOfEvent;
    private LocalDateTime dateTimeOfEvent;
    private String location;
    private double price;
    private int numberOfTickets;
    private String thumbnail;

}
