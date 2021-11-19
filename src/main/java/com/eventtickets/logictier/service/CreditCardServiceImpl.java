package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.network.CreditCardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CreditCardServiceImpl implements CreditCardService
{

  @NonNull
  private Validator validator;

  @NonNull
  private CreditCardRepository creditCardRepository;

  @Override
  public CreditCard addCreditCard(CreditCard creditCard)
  {
    Set<ConstraintViolation<CreditCard>> violations = validator
        .validate(creditCard);
    for (ConstraintViolation<CreditCard> violation : violations)
    {
      throw new IllegalArgumentException(violation.getMessage());
    }

    return creditCardRepository.createCreditCard(creditCard);
  }

  @Override
  public void removeCreditCard(CreditCard creditCard)
  {
    // TODO
  }

  @Override
  public List<CreditCard> getCreditCardsForUser(long ownerId)
  {
    return creditCardRepository.getByOwnerId(ownerId);
  }
}
