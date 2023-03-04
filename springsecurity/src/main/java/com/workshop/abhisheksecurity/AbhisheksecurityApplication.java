package com.workshop.abhisheksecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.workshop.abhisheksecurity.controller")
public class AbhisheksecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbhisheksecurityApplication.class, args);
	}

}
