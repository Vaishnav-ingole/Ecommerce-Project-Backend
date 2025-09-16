package com.app.ecommerce.service.impl;

import org.springframework.stereotype.Service;

import com.app.ecommerce.dto.UserRegisterRequestDto;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.repository.UserRepository;
import com.app.ecommerce.service.AuthService;
import com.app.ecommerce.service.UserService;

@Service
public class AuthServiceImpl implements AuthService{
	
	private UserService userService;
	private UserRepository userRepository;

	public AuthServiceImpl(UserService userService,UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}



	@Override
	public boolean login(UserRegisterRequestDto dto) {
		if(userRepository.existsByEmail(dto.getEmail())) {
			User user = userRepository.findByEmail(dto.getEmail());
			
			if(user.getPassword().equals(dto.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
