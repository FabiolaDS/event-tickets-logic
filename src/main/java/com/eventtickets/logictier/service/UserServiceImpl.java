package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.network.UserRepository;
import com.eventtickets.logictier.service.dto.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  @Override public User registerUser(RegisterUserDto userDto)
  {
    User user = new User(userDto.getEmail(), userDto.getFullName(),
        userDto.getPassword());
    return userRepository.createUser(user);

  }
}
