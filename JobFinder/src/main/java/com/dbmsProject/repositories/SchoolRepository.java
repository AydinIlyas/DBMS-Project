package com.dbmsProject.repositories;

import com.dbmsProject.domain.Employee;
import com.dbmsProject.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School,Long> {
    @Query("SELECT u FROM School u WHERE u.name = ?1")
    Optional<School> findByName(String name);

//    @Query("SELECT u FROM employee_school u WHERE u.employee_id = ?1")
//    List<Employee> findEmployees(Long id);
}
