package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component public class RestUserRepository implements UserRepository
{
  private RestTemplate restTemplate;
  private String dataUrl;

  public RestUserRepository(@Value("${eventTicket.data.url}") String dataUrl,
      RestTemplate restTemplate)
  {
    this.dataUrl = dataUrl;
    this.restTemplate = restTemplate;
  }

  @Override public User createUser(User user)
  {
    return restTemplate.postForObject(dataUrl + "/users", user, User.class);
  }

  @Override public User findByEmail(String email)
  {
    try
    {
      return restTemplate
          .getForObject(dataUrl + "/users", User.class, "email", email);
    } catch(HttpClientErrorException.BadRequest ex) {
      throw new IllegalArgumentException("No user with email " + email);
    }
  }
}
