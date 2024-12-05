package com.example.pfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.pfm.model")
@EnableJpaRepositories(basePackages = "com.example.pfm.repository")
@ComponentScan(basePackages = "com.example.pfm")
public class PersonalFinanceManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalFinanceManagementApplication.class, args);
    }
}
