package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.network.EventRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService
{
  private EventRepository eventRepository;

  public EventServiceImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }
  @Override public List<Event> getAllEvents()
  {
    return eventRepository.getAllEvents();
  }

  @Override public Event addEvent(Event event)
  {
    return eventRepository.addEvent(event);
  }
}
