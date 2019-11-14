package com.heraizen.dhi.tms.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAudtiting implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
    	// needs to get user name from security context
        return Optional.of("admin");
    }

}