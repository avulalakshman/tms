package com.spaneos.dhi.tms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spaneos.dhi.tms.user.service.BulkLoadUserServiceFactory;

@Configuration
public class AppConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public BulkLoadUserServiceFactory loadUserFactory() {
		BulkLoadUserServiceFactory factory = new BulkLoadUserServiceFactory();
		return factory;
	}

	
}
