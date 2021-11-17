package com.eventtickets.logictier.network;

 import com.eventtickets.logictier.model.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class RestTicketRepository extends RestRepository implements TicketRepository {

    public RestTicketRepository (RestTemplate restTemplate, @Value("${eventTicket.data.url}") String dataUrl){
        super(restTemplate,dataUrl,"tickets");
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
       return  rest().postForObject(url(), ticket,Ticket.class);
    }

    @Override
    public List<Ticket> getByUserId(long id) {
        ResponseEntity<List<Ticket>> response = rest().exchange(url("byUser", id),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {});
        return response.getBody();
    }

    @Override
    public Ticket getByEventIdAndUserId(long eventId, long userId) {
        //TODO
        return null;
    }

}
