package com.dbmsProject.repositories;

import com.dbmsProject.domain.Employee;
import com.dbmsProject.domain.Employer;
import com.dbmsProject.domain.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KullaniciRepository<T extends Kullanici> extends JpaRepository<T,Long> {
    public List<Kullanici> findAllByOrderByIdAsc();
    Optional<Employer> findEmployerById(Long id);
    Optional<Employee> findEmployeeById(Long id);

    @Query("Select u From Employee u Where u.username=?1")
    Optional<Employee> findEmployeeByUserName(String userName);
    @Query("Select u From Employee u Where u.email=?1")
    Optional<Employee> findEmployeeByEmail(String email);
    @Query("Select u From Employer u Where u.username=?1")
    Optional<Employer> findEmployerByUserName(String userName);
    @Query(value="Select u From Employee u")
    List<Employee> findEmployee();

    @Query(value="Select u From Employer u")
    List<Employer> findEmployer();
    @Query(value="Select u from Kullanici u Where u.username LIKE %?1%")
    List<Kullanici> findAllByUserName(String username);
    boolean existsByName(String name);
}
