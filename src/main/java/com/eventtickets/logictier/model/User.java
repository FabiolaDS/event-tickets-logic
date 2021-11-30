package com.eventtickets.logictier.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User
{

  private Long id;
  @Email(message = "The email is not valid")
  @NotBlank(message = "The email cannot be empty")
  private String email;

  @NotBlank(message = "The full name cannot be empty")
  @Size(min = 5, max = 150, message = "The full name should be between 5 and 150 character long")
  private String fullName;

  @NotBlank(message = "The password should not be empty")
  @Size(min = 6, max = 20, message = "The password should be between 6 and 20 character long")
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
