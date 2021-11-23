package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.CreditCard;

import java.util.List;

public interface CreditCardRepository {
    CreditCard createCreditCard(long userId, CreditCard creditCard);
    List<CreditCard> getByOwnerId(long ownerId);



}
