package com.exo2.Exercice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Exercice2Application {

	public static void main(String[] args) {
		SpringApplication.run(Exercice2Application.class, args);
	}

}
