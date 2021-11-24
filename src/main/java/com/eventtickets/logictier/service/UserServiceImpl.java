package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.User;
import com.eventtickets.logictier.network.UserRepository;
import com.eventtickets.logictier.service.dto.LoginUserDTO;
import com.eventtickets.logictier.service.dto.RegisterUserDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private Validator validator;

	public UserServiceImpl(UserRepository userRepository, Validator validator) {
		this.userRepository = userRepository;
		this.validator = validator;
	}

	@Override
	public User registerUser(RegisterUserDTO userDto) {

		Set<ConstraintViolation<RegisterUserDTO>> violations = validator
			.validate(userDto);
		for (ConstraintViolation<RegisterUserDTO> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		if (userRepository.findByEmail(userDto.getEmail()) != null) {
			throw new IllegalArgumentException("Email address already in use");
		}
		User user = new User(userDto.getEmail(), userDto.getFullName(),
			userDto.getPassword());
		return userRepository.createUser(user);

	}

	@Override
	public User login(LoginUserDTO loginUserDTO) {
		Set<ConstraintViolation<LoginUserDTO>> violations = validator
			.validate(loginUserDTO);
		for (ConstraintViolation<LoginUserDTO> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		User u = userRepository.findByEmail(loginUserDTO.getEmail());
		if (u == null)
			throw new IllegalArgumentException("Login incorrect");
		if (u.getPassword().equals(loginUserDTO.getPassword()))
			return u;

		throw new IllegalArgumentException("Incorrect password");

	}

	@Override
	public User updateUserData(User user) {
		Set<ConstraintViolation<User>> violations = validator
			.validate(user);

		for (ConstraintViolation<User> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}
		return userRepository.updateUser(user.getId(), user);
	}
}
