package com.example.pfm.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Personal Finance Management API")
                        .version("1.0")
                        .description("API documentation for the Personal Finance Management application")
                        .contact(new Contact()
                                .name("Uddeshya")
                                .email("uddeshyatyagi775@gmail.com")
                                .url("https://linkedin.com/in/uddeshya-tyagi-hariom1711"))
                );
    }
}
