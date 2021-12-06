package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDTO;

import java.util.List;

public interface UserService {
	User registerUser(RegisterUserDTO userDto);
	User login(LoginUserDTO loginUserDTO);
	User updateUserData(User user);
	User grantAdminPrivilege(long userId);
	List<User> getAllUsers();
	User removeAdminPrivilege(long userId);
}
