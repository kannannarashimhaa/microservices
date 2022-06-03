package com.service.delete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DeleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeleteApplication.class, args);
	}

}
