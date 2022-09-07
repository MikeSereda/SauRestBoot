package ru.sereda.saurestboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"ru.sereda.saurestboot"})
public class SauRestBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SauRestBootApplication.class, args);
    }



}
