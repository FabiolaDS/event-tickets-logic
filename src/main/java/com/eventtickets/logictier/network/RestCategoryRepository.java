package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Category;
import com.eventtickets.logictier.model.Event;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestCategoryRepository extends RestRepository
	implements CategoryRepository {
	public RestCategoryRepository(
		@NonNull RestTemplate restTemplate,
		@Value("${eventTicket.data.url}") String dataUrl) {
		super(restTemplate, dataUrl, "/categories");
	}

	@Override
	public Category createCategory(Category category) {
		return rest().postForObject(url(), category, Category.class);
	}

	@Override
	public List<Category> getAllCategories() {
		ResponseEntity<List<Category>> response = rest()
			.exchange(url(),
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Category>>() {
				});
		return response.getBody();
	}

	@Override
	public Category findByName(String category) {
		try {
			return rest()
				.getForObject(url("/byName?name={category}"), Category.class,
					category);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}
}
