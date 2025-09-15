package com.app.ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EcommerceCommonConfig {
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	//to-DO -> add passwordEncoder bean
	
}
