package com.app.ecommerce.service;

import com.app.ecommerce.dto.UserRegisterRequestDto;

public interface AuthService {

	boolean login(UserRegisterRequestDto dto);

}
