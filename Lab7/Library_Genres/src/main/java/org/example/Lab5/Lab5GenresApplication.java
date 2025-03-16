package org.example.Lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Lab5GenresApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(Lab5GenresApplication.class, args);
    }
}
