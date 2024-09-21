package com.ALOTs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  //ask the springboot to give all the methods that has tranctional on them
public class AloTsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AloTsApplication.class, args);
	}

	@Bean //indicates that this method instantiates, configures and initializes a new object to be managed by spring IoC container.
	public PlatformTransactionManager PTM(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}
