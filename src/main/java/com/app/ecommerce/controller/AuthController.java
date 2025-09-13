package com.app.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.dto.UserRegisterRequestDto;
import com.app.ecommerce.dto.UserRegisterResponseDto;
import com.app.ecommerce.service.AuthService;
import com.app.ecommerce.service.UserService;

@RestController
@RequestMapping("ecommerce/auth")
public class AuthController {
	
	private UserService userService;
	private AuthService authService;
		
	public AuthController(UserService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto userRegisterDto){
		UserRegisterResponseDto response = userService.registerUser(userRegisterDto);
		return response.isStatus()? new ResponseEntity<UserRegisterResponseDto>(response,HttpStatus.CREATED):
				new ResponseEntity<UserRegisterResponseDto>(response,HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserRegisterRequestDto dto){
		
		return authService.login(dto)?new ResponseEntity<String>("login Success",HttpStatus.OK):
				new ResponseEntity<String>("login Failed",HttpStatus.BAD_REQUEST);
	}
}
