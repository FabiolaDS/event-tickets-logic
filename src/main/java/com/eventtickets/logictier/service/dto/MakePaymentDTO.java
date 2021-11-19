package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MakePaymentDTO
{
    @NotBlank(message = "The credit card number cannot be empty")
    @CreditCardNumber
    private String creditCardNumber;
    @Positive
    private long event;
    @Positive
    private long buyer;
}


