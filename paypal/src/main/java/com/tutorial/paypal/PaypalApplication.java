package com.tutorial.paypal;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaypalApplication {

	public static void main(String[] args) {
		//load .env file
		Dotenv dotenv = Dotenv.configure().load();
		//set env vars
		dotenv.entries().forEach( entry -> System.setProperty(
				entry.getKey(), entry.getValue()
		));

		SpringApplication.run(PaypalApplication.class, args);
	}

}
