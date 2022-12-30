package com.dbmsProject.repositories;

import com.dbmsProject.domain.District;
import com.dbmsProject.domain.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement,Long> {

}
