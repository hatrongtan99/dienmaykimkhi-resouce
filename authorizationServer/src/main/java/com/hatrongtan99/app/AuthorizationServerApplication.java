package com.hatrongtan99.app;

import com.hatrongtan99.app.entity.RoleEntity;
import com.hatrongtan99.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class AuthorizationServerApplication {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (args) -> {
            RoleEntity roleUser = this.roleRepository.findByName("USER")
                    .orElse(new RoleEntity("USER", true));
            this.roleRepository.save(roleUser);
        };
    }
}
