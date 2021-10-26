package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.User;

public interface UserRepository
{
  User createUser(User user);
  User findByEmail(String email);
  User updateUser(Long id, User user);
}
