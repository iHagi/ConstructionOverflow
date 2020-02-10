package com.construction.data.repositories;

import com.construction.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Long> {

    boolean existsByName(String name);

    Optional<Company> getByNameIgnoreCase(String name);

}
