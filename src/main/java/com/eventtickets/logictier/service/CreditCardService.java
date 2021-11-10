package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    CreditCard addCreditCard(CreditCard creditCard);
    void removeCreditCard(CreditCard creditCard);
    List<CreditCard> getCreditCardsForUser(long ownerId);

}
