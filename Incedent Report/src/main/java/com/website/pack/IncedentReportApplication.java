package com.website.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableWebMvc
@EnableWebSecurity
public class IncedentReportApplication {
	
	
	@GetMapping("/")
	public String index() {
		return "Index";
	}

	public static void main(String[] args) {
		SpringApplication.run(IncedentReportApplication.class, args);
	}

}
