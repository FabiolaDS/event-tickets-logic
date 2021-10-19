package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;

import java.util.List;

public interface EventRepository
{
  List<Event> getAllEvents();
//  Event getEventById(Long id);
  Event addEvent(Event event);
}
