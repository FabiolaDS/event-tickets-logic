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

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RestEventRepository extends RestRepository
	implements EventRepository {

	public RestEventRepository(@Value("${eventTicket.data.url}") String dataUrl,
		RestTemplate restTemplate) {
		super(restTemplate, dataUrl, "events");
	}

	@Override
	public List<Event> findByTimeOfTheEventAfter(LocalDateTime localDateTime) {
		ResponseEntity<List<Event>> response = rest()
			.exchange(url("?after=" + localDateTime),
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Event>>() {
				});

		return response.getBody();
	}

	@Override
	public Event getEventById(Long id) {
		try {
			return rest().getForObject(url(id), Event.class);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public Event addEvent(Event event) {
		return rest().postForObject(url(), event, Event.class);
	}

	@Override
	public Event updateEvent(Long id, Event event) {
		try {
			return rest().patchForObject(url(id), event, Event.class);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public List<Event> findByCategoryIdAndTimeOfTheEventAfter(long id,
		LocalDateTime localDateTime) {
		ResponseEntity<List<Event>> response = rest().exchange(
			url("byCategoryAndTime/?categoryId={id}&dateTime={dateTime}"),
			HttpMethod.GET, null,
			new ParameterizedTypeReference<List<Event>>() {
			}, id, localDateTime);

		return response.getBody();
	}

	@Override
	public List<User> getParticipants(long eventId) {
		try {
			ResponseEntity<List<User>> response = rest().exchange(
				url("{eventId}/participants"),
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				}, eventId);

			return response.getBody();
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}

	}

	@Override
	public Event findByName(String name) {
		try {
			return rest()
				.getForObject(url("/byName?name={name}"), Event.class, name);
		}
		catch (HttpClientErrorException.NotFound e) {
			return null;
		}
	}

	@Override
	public List<Event> findByLocationAndTimeOfTheEventAfter(String location,
		LocalDateTime timeOfTheEvent) {
		ResponseEntity<List<Event>> response = rest().exchange(
			url("byLocationAndTime/?location={location}&dateTime={dateTime}"),
			HttpMethod.GET, null,
			new ParameterizedTypeReference<List<Event>>() {
			}, location, timeOfTheEvent);

		return response.getBody();
	}
}
