package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDTO
{
  private String email;
  private String password;
}
