package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.network.CreditCardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class CreditCardServiceImpl implements CreditCardService {

    @NonNull
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {
        return creditCardRepository.createCreditCard(creditCard);
    }

    @Override
    public void removeCreditCard(CreditCard creditCard) {
        // TODO
    }

    @Override
    public List<CreditCard> getCreditCardsForUser(long ownerId) {
        return creditCardRepository.getByOwnerId(ownerId);
    }
}
