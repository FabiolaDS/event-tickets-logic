package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.Notification;
import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.network.EventRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	@NonNull
	private EventRepository eventRepository;
	@NonNull
	private NotificationService notificationService;
	@NonNull
	private Validator validator;

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
		event = eventRepository.updateEvent(eventId, event);
		Notification notification = new Notification("Event Cancellation",
			String.format("Event %s has been cancelled", event.getName()),
			LocalDateTime.now());
		List<Long> userIds = eventRepository.getParticipants(eventId).stream()
			.map(User::getId).collect(
				Collectors.toList());
		notificationService.notify(notification, userIds);
		return event;
	}

	@Override
	public List<Event> findUpcomingEventsByCategory(long categoryId) {
		return eventRepository
			.findByCategoryIdAndTimeOfTheEventAfter(categoryId,
				LocalDateTime.now());
	}

}
