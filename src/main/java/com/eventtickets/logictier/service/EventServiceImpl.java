package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.network.EventRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService
{
  private EventRepository eventRepository;
  @NonNull
  private Validator validator;

  public EventServiceImpl(EventRepository eventRepository)
  {
    this.eventRepository = eventRepository;
  }

  @Override
  public List<Event> getAllEvents()
  {
    return eventRepository.getAllEvents();
  }

  @Override
  public Event addEvent(Event event)
  {
    Set<ConstraintViolation<Event>> violations = validator.validate(event);
    for (ConstraintViolation<Event> violation : violations)
    {
      throw new IllegalArgumentException(violation.getMessage());
    }
    return eventRepository.addEvent(event);
  }

  @Override
  public Event getById(Long id)
  {
    return eventRepository.getEventById(id);
  }
}
