package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RestEventRepository extends RestRepository implements EventRepository {

    public RestEventRepository(@Value("${eventTicket.data.url}") String dataUrl, RestTemplate restTemplate) {
        super(restTemplate, dataUrl, "events");
    }

    @Override
    public List<Event> findByTimeOfTheEventAfter(LocalDateTime localDateTime)
    {
        ResponseEntity<List<Event>> response = rest().exchange(url("?after=" + localDateTime),
            HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>() {
            });

        return response.getBody();
    }


    @Override
    public Event getEventById(Long id) {
        return rest().getForObject(url(id), Event.class);
    }

    @Override
    public Event addEvent(Event event) {
        return rest().postForObject(url(), event, Event.class);
    }
}
