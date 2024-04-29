package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LibraryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryClientApplication.class, args);

		System.out.printf("Library Client address: %n%s%n", "http://localhost:8070");
	}

}
