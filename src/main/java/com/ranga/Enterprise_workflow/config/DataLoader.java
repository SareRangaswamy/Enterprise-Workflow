package com.ranga.Enterprise_workflow.config;

import com.ranga.Enterprise_workflow.entity.Role;
import com.ranga.Enterprise_workflow.repository.Rolerepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final Rolerepository roleRepository;

    public DataLoader(Rolerepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        if (roleRepository.count() == 0) {

            Role admin = new Role();
            admin.setRoleName("ROLE_ADMIN");

            roleRepository.save(admin);

            System.out.println("ROLE_ADMIN inserted successfully");
        }

    }
}