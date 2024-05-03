package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args)  {
		SpringApplication.run(EurekaServerApplication.class);
		System.out.printf("Eureka Server address: %n%s%n", "http://localhost:8761");
	}

}