package com.spaneos.dhi.tms.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAudtiting implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
    	//TODO needs to get user name from security context
        return Optional.of("admin");
    }

}