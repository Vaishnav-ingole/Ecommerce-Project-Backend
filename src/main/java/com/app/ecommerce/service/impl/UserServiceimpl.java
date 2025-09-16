package com.app.ecommerce.service.impl;

import org.springframework.stereotype.Service;

import com.app.ecommerce.dto.UserRegisterRequestDto;
import com.app.ecommerce.dto.UserRegisterResponseDto;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.repository.UserRepository;
import com.app.ecommerce.service.UserService;

@Service
public class UserServiceimpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceimpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterDto) {
		UserRegisterResponseDto registerRsponseDto = new UserRegisterResponseDto();

		if (userRegisterDto.getEmail() != null && userRepository.existsByEmail(userRegisterDto.getEmail())) {
			registerRsponseDto.setMessage("user already exist with email : " + userRegisterDto.getEmail());
			registerRsponseDto.setStatus(Boolean.FALSE);

			return registerRsponseDto;
		}
		User user = new User();
		user.setEmail(userRegisterDto.getEmail());
		user.setPassword(userRegisterDto.getPassword());

		try {
		User savedUser = userRepository.save(user);
		// To-do -> trigger a notification mail to the given Id

		registerRsponseDto.setMessage("user registered Succesfully with email : " + savedUser.getEmail());
		registerRsponseDto.setStatus(Boolean.TRUE);
		}
		catch (Exception e) {
			registerRsponseDto.setMessage("Something went wrong While Registering user with email : " + userRegisterDto.getEmail());
			registerRsponseDto.setStatus(Boolean.FALSE);
		}
		
		return registerRsponseDto;
	}
	
		
	
}
