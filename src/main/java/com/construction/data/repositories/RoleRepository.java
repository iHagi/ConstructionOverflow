package com.construction.data.repositories;

import com.construction.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
        Role findByName(String name);
}

