package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibraryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServerApplication.class, args);
		System.out.printf("Library Server Application address: %n%s%n", "http://localhost:8090");
	}

}
