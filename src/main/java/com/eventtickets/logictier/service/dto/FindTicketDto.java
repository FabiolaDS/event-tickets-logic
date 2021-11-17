package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class FindTicketDto {
    private long buyerId;
    private long eventId;


}
