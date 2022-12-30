package com.dbmsProject.repositories;

import com.dbmsProject.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position,Long> {

    @Query("Select p From Position p Where p.name=?1")
    Optional<Position> findByName(String name);
}
