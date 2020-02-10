package com.construction.data.repositories;

import com.construction.data.models.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EngineersRepository extends JpaRepository<Engineer, Long> {

    boolean existsByName(String name);

    Optional<Engineer> getByNameIgnoreCase(String name);

}
