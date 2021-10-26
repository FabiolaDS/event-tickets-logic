package com.eventtickets.logictier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User
{

  private Long id;
  @NonNull private String email;
  @NonNull private String fullName;
  @NonNull private String password;

  private boolean isAdmin;

}