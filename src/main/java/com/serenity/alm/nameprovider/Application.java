package com.serenity.alm.nameprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serenity.alm.nameprovider.controllers.NameProviderController;

@SpringBootApplication
public class Application {
    // Starts the controller
    public static void main(String[] args) {
        SpringApplication.run(NameProviderController.class, args);
    }

}

