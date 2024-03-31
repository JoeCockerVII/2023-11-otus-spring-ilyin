package ru.otus.hw;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.h2.tools.Console;

import java.sql.SQLException;

@EnableMongock
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Application.class, args);
		Console.main(args);
	}
}
