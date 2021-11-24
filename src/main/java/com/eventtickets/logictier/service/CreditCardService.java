package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.service.dto.CreateCardDTO;

import java.util.List;

public interface CreditCardService {
    CreditCard addCreditCard(CreateCardDTO creditCardDto);
    void removeCreditCard(long id);
    List<CreditCard> getCreditCardsForUser(long ownerId);

}
