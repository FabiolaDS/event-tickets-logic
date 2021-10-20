package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class RestEventRepository implements EventRepository
{
  private RestTemplate restTemplate;
  private String dataUrl;

  public RestEventRepository(@Value("${eventTicket.data.url}") String dataUrl, RestTemplate restTemplate) {
    this.dataUrl = dataUrl;
    this.restTemplate = restTemplate;
  }

  @Override public List<Event> getAllEvents()
  {
    ResponseEntity<List<Event>> response = restTemplate.exchange(dataUrl + "/events",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>()
        {});


    return response.getBody();
  }

  @Override public Event addEvent(Event event)
  {

    return restTemplate.postForObject(dataUrl + "/events",event, Event.class);
  }
}
