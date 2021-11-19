package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDTO;

public interface UserService
{
  User registerUser(RegisterUserDTO userDto);
  User login(LoginUserDTO loginUserDTO);
  User updateUserData(User user);
}
