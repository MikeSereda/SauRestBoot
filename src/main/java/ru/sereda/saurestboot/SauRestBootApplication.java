package ru.sereda.saurestboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.sereda.saurestboot"})
public class SauRestBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SauRestBootApplication.class, args);
    }



}
