package com.dbmsProject.repositories;

import com.dbmsProject.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByNameAndSince(String name, LocalDate date);
}
