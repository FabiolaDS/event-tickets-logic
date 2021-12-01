package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository
{
  List<Event> findByTimeOfTheEventAfter(LocalDateTime localDateTime);
 Event getEventById(Long id);
  Event addEvent(Event event);
  Event updateEvent(Long id, Event event);
  List<Event> findByCategoryIdAndTimeOfTheEventAfter(long id, LocalDateTime localDateTime);

}
