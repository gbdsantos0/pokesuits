package com.dbc.pokesuits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PokesuitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokesuitsApplication.class, args);
	}

}
