package com.dbmsProject.repositories;

import com.dbmsProject.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District,Long> {
    Optional<District> findByName(String name);
    Optional<District> findByNameAndCity_Id(String name,Integer cityId);

}
