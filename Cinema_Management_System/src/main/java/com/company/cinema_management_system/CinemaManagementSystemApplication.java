package com.company.cinema_management_system;

import com.company.cinema_management_system.repository.ManagerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = ManagerRepository.class)
public class CinemaManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementSystemApplication.class, args);
    }

}
