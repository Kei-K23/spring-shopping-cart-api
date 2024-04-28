package com.example.shopping.cart.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
