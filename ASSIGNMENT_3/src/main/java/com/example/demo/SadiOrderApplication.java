package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.example.demo"})
@EnableJpaAuditing
@ComponentScan({"com.example.demo.controller", "com.example.demo.repository"})
@EntityScan("com.example.demo.model")
@EnableJpaRepositories("com.example.demo.repository")
@ComponentScan("com.example.demo")
public class SadiOrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(SadiOrderApplication.class, args);
    }

}
