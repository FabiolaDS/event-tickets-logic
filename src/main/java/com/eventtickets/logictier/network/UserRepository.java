package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.User;

import java.util.List;

public interface UserRepository
{
  User createUser(User user);
  User findByEmail(String email);
  User updateUser(Long id, User user);
  User findById(long id);
  List<User> getAllUsers();
}
