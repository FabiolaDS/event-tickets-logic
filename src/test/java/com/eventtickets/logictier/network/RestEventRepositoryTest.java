package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestEventRepositoryTest
{

  @Autowired private EventRepository eventRepository;

  @Test void getUpcomingEvents()
  {

    List<Event> eventList;
    eventList = eventRepository.findByTimeOfTheEventAfter(LocalDateTime.now());
    System.out.println(eventList);
    assertTrue(eventList.size() > 0);
  }
}