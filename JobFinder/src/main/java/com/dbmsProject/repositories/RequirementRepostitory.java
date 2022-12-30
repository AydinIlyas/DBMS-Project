package com.dbmsProject.repositories;

import com.dbmsProject.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepostitory extends JpaRepository<Requirement,Long> {

}
