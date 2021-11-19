package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor

public class FindTicketDTO
{
    @Positive
    private long buyerId;
    @Positive
    private long eventId;


}
