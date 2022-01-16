package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "Gabriel", "OBRABO01", "12345",new ArrayList<>()));
			userService.saveUser(new User(null, "Marcelo", "TREMETERRA", "12345",new ArrayList<>()));
			userService.saveUser(new User(null, "Isabel", "IZITA", "12345",new ArrayList<>()));
			userService.saveUser(new User(null, "Flávia", "DAFEIRA", "12345",new ArrayList<>()));
			
			
			userService.addRoleToUser("OBRABO01", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("TREMETERRA", "ROLE_ADMIN");
			userService.addRoleToUser("IZITA", "ROLE_USER");
			userService.addRoleToUser("DAFEIRA", "ROLE_MANAGER");
			
		};
	}
	
	
	
	
	
	
	
	
}
