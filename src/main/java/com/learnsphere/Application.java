package com.learnsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learnsphere.entities.Users;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Users u = new Users();
		SpringApplication.run(Application.class, args);
	}

}
