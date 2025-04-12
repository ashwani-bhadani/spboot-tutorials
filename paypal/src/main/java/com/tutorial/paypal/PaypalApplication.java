package com.tutorial.paypal;

import com.tutorial.paypal.controller.PaypalController;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

import static java.util.TimeZone.*;

/**
 * paypal recommends comunicating to it through APIs, TODO: implement feign client API comms in this.
 */
@SpringBootApplication
public class PaypalApplication {
	private static final Logger log = LoggerFactory.getLogger(PaypalApplication.class);

	@Value("${app.timezone:Asia/Kolkata}") //providing default value if missing
	private String appTimeZone; //cannot be static & final, find a work-around

	public static void main(String[] args) {
		//load .env file
		Dotenv dotenv = Dotenv.configure().load();
		//set env vars
		dotenv.entries().forEach( entry -> System.setProperty(
				entry.getKey(), entry.getValue()
		));

		SpringApplication.run(PaypalApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(getTimeZone(appTimeZone));
		log.info("Application startup with default timezone: {}", TimeZone.getDefault().getID());
	}

}
