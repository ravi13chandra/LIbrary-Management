package com.example.Libary_Management_System.config;

import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private UserService userService;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            if(!userService.hasUsers()) {
                Users adminUser = new Users();
                adminUser.setUsername("DEANSTAR");
                adminUser.setPassword("Dean@125");
                adminUser.setRole("ADMIN");
                userService.saveUser(adminUser);
            }
        };
    }
}
