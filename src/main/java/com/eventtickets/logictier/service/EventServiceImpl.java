package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.network.EventRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {
	private EventRepository eventRepository;
	private Validator validator;

	public EventServiceImpl(EventRepository eventRepository,
		Validator validator) {
		this.eventRepository = eventRepository;
		this.validator = validator;
	}

	@Override
	public List<Event> findUpcomingEvents() {
		return eventRepository.findByTimeOfTheEventAfter(LocalDateTime.now());
	}

	@Override
	public Event addEvent(Event event) {
		Set<ConstraintViolation<Event>> violations = validator.validate(event);
		for (ConstraintViolation<Event> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		return eventRepository.addEvent(event);
	}

	@Override
	public Event getById(Long id) {
		return eventRepository.getEventById(id);
	}

	@Override
	public Event updateEvent(Event event) {
		Set<ConstraintViolation<Event>> violations = validator.validate(event);
		for (ConstraintViolation<Event> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		return eventRepository.updateEvent(event.getId(), event);
	}

	@Override
	public Event cancelEvent(Long eventId) {
		Event event = new Event();
		event.setIsCancelled(true);
		return eventRepository.updateEvent(eventId, event);
	}

	@Override
	public List<Event> findUpcomingEventsByCategory(long categoryId) {
		return eventRepository
			.findByCategoryIdAndTimeOfTheEventAfter(categoryId,
				LocalDateTime.now());
	}
}
