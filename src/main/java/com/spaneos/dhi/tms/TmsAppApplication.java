package com.spaneos.dhi.tms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TmsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsAppApplication.class, args);
	}

}
