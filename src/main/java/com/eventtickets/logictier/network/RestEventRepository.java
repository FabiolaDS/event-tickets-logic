package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
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
  private static final String URL = "http://localhost:8081";

  public RestEventRepository(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override public List<Event> getAllEvents()
  {
    ResponseEntity<List<Event>> response = restTemplate.exchange(URL + "/events",
        HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>()
        {});


    return response.getBody();
  }
}
