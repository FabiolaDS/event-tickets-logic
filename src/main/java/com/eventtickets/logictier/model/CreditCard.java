package com.eventtickets.logictier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvv;
    private String cardOwnerName;
    private long ownerId;
}
