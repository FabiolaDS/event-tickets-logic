package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterUserDTO
{
  @Email(message = "The email is not valid")
  private String email;
  @NotBlank(message = "The full name should not be empty")
  @Size(min = 5, max = 150, message = "The full name should be between 5 and 150 character long")
  private String fullName;
  @NotBlank(message = "The password should not be empty")
  @Size(min = 6, max = 20, message = "The password should be between 6 and 20 character long")
  private String password;

}



