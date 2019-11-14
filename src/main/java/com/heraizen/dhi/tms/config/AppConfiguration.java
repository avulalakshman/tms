package com.heraizen.dhi.tms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heraizen.dhi.tms.user.service.BulkLoadUserServiceFactory;

@Configuration
public class AppConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public BulkLoadUserServiceFactory loadUserFactory() {
		return new BulkLoadUserServiceFactory();
	}


	
}
