package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.printf("Application address: %n%s%n", "http://localhost:8090/");
		System.out.printf("Actuator address: %n%s%n", "http://localhost:8090/actuator");
	}

}
