package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.network.UserRepository;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDto;
import org.springframework.stereotype.Service;

@Service public class UserServiceImpl implements UserService
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
    if(userRepository.findByEmail(userDto.getEmail()).getEmail().equals(user.getEmail()))
    {
      throw new IllegalArgumentException("User with this email is already registered");
    }
    return userRepository.createUser(user);

  }

  @Override public User login(LoginUserDTO loginUserDTO)
  {
    User u = userRepository.findByEmail(loginUserDTO.getEmail());
    if (u == null)
      throw new IllegalArgumentException("Login incorrect");
    if (u.getPassword().equals(loginUserDTO.getPassword()))
      return u;

    throw new IllegalArgumentException("Incorrect password");

  }

  @Override public User updateUserData(User user)
  {
    return userRepository.updateUser(user.getId(), user);
  }
}
