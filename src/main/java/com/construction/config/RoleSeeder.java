package com.construction.config;

import com.construction.data.models.Role;
import com.construction.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RoleSeeder {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.roleRepository.findAll().isEmpty()) {
            Role userRole = new Role();
            userRole.setName("USER");

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            Role moderatorRole = new Role();
            moderatorRole.setName("MODERATOR");

            this.roleRepository.save(userRole);
            this.roleRepository.save(adminRole);
            this.roleRepository.save(moderatorRole);
        }
    }


}
