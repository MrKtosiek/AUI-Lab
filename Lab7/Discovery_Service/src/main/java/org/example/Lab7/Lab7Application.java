package org.example.Lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Lab7Application {
	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}
}
