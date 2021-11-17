package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MakePaymentDto {
    private String creditCardNumber;
    private long event;
    private long buyer;
}
