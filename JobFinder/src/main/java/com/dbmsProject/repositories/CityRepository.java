package com.dbmsProject.repositories;

import com.dbmsProject.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
