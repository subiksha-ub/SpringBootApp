package com.example.employeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableCaching
@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
				SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
