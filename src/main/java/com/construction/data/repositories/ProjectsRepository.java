package com.construction.data.repositories;

import com.construction.data.models.Engineer;
import com.construction.data.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String name);

    Optional<Project> getByNameIgnoreCase(String name);

    List<Project> getByUserUsername(String username);

}
