package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginUserDTO
{
  @Email(message = "Please provide a valid email")
  @NotBlank(message = "The email cannot be empty")
  private String email;
  @NotBlank(message = "The password cannot be empty")
  private String password;
}
