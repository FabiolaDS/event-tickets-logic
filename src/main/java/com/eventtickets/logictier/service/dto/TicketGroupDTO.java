package com.eventtickets.logictier.service.dto;

import com.eventtickets.logictier.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketGroupDTO
{
  private Long eventId;
  private String nameOfEvent;
  private LocalDateTime timeOfTheEvent;
  private String thumbnail;
  private String location;
  private double ticketPrice;
  private List<Ticket> tickets;

}
