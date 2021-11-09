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
public class RestTicketRepository implements TicketRepository {
    private RestTemplate restTemplate;
    private String dataUrl;

    public RestTicketRepository (RestTemplate restTemplate, @Value("${eventTicket.data.url}") String dataUrl){
        this.restTemplate= restTemplate;
        this.dataUrl= dataUrl;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
       return  restTemplate.postForObject(dataUrl+ "/tickets", ticket,Ticket.class);
    }

    @Override
    public List<Ticket> getByUserId(long id) {
        ResponseEntity<List<Ticket>> response = restTemplate.exchange(dataUrl + "/tickets/byUser/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>()
                {});
        return response.getBody();
    }

}
