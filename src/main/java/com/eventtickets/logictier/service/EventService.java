package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> findUpcomingEvents();
    Event addEvent(Event event);
    Event getById(Long id);
    Event updateEvent(Event event);
    Event cancelEvent(Long eventId);
    List<Event> findUpcomingEventsByCategory(long categoryId);

}
