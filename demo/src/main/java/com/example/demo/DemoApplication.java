package com.example.demo;

import com.example.demo.enitity.*;
import com.example.demo.repo.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(RoleRepository roleRepo) {
		return (args) -> {

			roleRepo.saveAll(List.of(
			new Roles("ROLE_EMPLOYEE"),new Roles("ROLE_PARTNER"),new Roles("ROLE_CUSTOMER")));
		};
	}
}
