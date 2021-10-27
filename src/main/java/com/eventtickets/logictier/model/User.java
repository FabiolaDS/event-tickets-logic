package com.eventtickets.logictier.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL) public class User
{

  private Long id;
  private String email;
  private String fullName;
  private String password;

  private boolean admin;

  public User(String email, String fullName, String password)
  {
    this.email = email;
    this.fullName = fullName;
    this.password = password;
    this.admin = false;
  }

}
