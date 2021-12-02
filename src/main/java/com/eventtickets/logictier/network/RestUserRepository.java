package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestUserRepository extends RestRepository
	implements UserRepository {

	public RestUserRepository(@Value("${eventTicket.data.url}") String dataUrl,
		RestTemplate restTemplate) {
		super(restTemplate, dataUrl, "/users");
	}

	@Override
	public User createUser(User user) {
		return rest().postForObject(url(), user, User.class);
	}

	@Override
	public User findByEmail(String email) {
		try {
			return rest()
				.getForObject(url("/byEmail?email={email}"), User.class, email);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public User updateUser(Long id, User user) {
		try {
			return rest()
				.patchForObject(url(id), user, User.class);
		}

		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public User findById(long id) {
		try {
			return rest().getForObject(url(id), User.class);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		ResponseEntity<List<User>> response = rest().exchange(
			url(),
			HttpMethod.GET, null,
			new ParameterizedTypeReference<List<User>>() {
			});

		return response.getBody();
	}
}
