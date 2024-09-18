package com.ALOTs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class AloTsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AloTsApplication.class, args);
	}

}
