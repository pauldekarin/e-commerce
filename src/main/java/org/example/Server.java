package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"org.example","org.example.controllers"})
//@EntityScan("org.example.entities")
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);

    }
}