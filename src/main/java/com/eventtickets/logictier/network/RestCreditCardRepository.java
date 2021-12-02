package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.CreditCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestCreditCardRepository extends RestRepository
	implements CreditCardRepository {

	public RestCreditCardRepository(
		@Value("${eventTicket.data.url}") String dataUrl,
		RestTemplate restTemplate) {
		super(restTemplate, dataUrl, "creditCards");
	}

	@Override
	public CreditCard createCreditCard(long userId, CreditCard creditCard) {
		return rest().postForObject(url(userId), creditCard, CreditCard.class);

	}

	@Override
	public List<CreditCard> getByOwnerId(long ownerId) {
		try {
			ResponseEntity<List<CreditCard>> response = rest()
				.exchange(url("user", ownerId),
					HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CreditCard>>() {
					});

			return response.getBody();
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}
}
