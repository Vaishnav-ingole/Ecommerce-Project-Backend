package com.app.ecommerce.service;

import com.app.ecommerce.dto.UserRegisterRequestDto;
import com.app.ecommerce.dto.UserRegisterResponseDto;

public interface UserService {

	UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterDto);

}
