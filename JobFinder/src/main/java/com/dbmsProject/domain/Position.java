package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String name;
    @ManyToMany(mappedBy = "positions")
    @JsonIgnore
    private Set<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "position")
    @JsonIgnore
    private Set<JobAdvertisement> jobAdvertisements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public void setJobAdvertisements(Set<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }
    public void removeEmployees(Employee employee)
    {
        employee.getPositions().remove(this);
       // employees.remove(employee);
    }
}
