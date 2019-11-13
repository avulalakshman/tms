package com.spaneos.dhi.tms;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@ComponentScan({"com.spaneos","com.heraizen"})
public class TmsAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TmsAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
