package ru.sereda.saurestboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"ru.sereda.saurestboot"})
@EnableDiscoveryClient
public class SauRestBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SauRestBootApplication.class, args);
    }



}
