package com.app.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.dto.UserRegisterRequestDto;
import com.app.ecommerce.dto.UserRegisterResponseDto;
import com.app.ecommerce.service.UserService;

@RestController
@RequestMapping("ecommerce/auth")
public class AuthController {
	
	private UserService userService;
		
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto userRegisterDto){
		UserRegisterResponseDto response = userService.registerUser(userRegisterDto);
		return response.isStatus()? new ResponseEntity<UserRegisterResponseDto>(response,HttpStatus.CREATED):
			new ResponseEntity<UserRegisterResponseDto>(response,HttpStatus.BAD_REQUEST);
	}

}
