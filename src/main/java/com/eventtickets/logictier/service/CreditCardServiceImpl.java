package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.network.CreditCardRepository;
import com.eventtickets.logictier.service.dto.CreateCardDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@NonNull
	private Validator validator;

	@NonNull
	private CreditCardRepository creditCardRepository;

	@Override
	public CreditCard addCreditCard(CreateCardDTO creditCardDto) {
		Set<ConstraintViolation<CreateCardDTO>> violations = validator
			.validate(creditCardDto);
		for (ConstraintViolation<CreateCardDTO> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}

		CreditCard creditCard = new CreditCard(null,
			creditCardDto.getCardNumber(),
			creditCardDto.getExpiryMonth(), creditCardDto.getExpiryYear(),
			creditCardDto.getCvv(), creditCardDto.getCardOwnerName());

		return creditCardRepository
			.createCreditCard(creditCardDto.getOwnerId(), creditCard);
	}

	@Override
	public void removeCreditCard(long id) {
		creditCardRepository.removeCreditCard(id);
	}

	@Override
	public List<CreditCard> getCreditCardsForUser(long ownerId) {
		return creditCardRepository.getByOwnerId(ownerId);
	}
}
