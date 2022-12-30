package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = (100))
    private String name;
    @ManyToMany(mappedBy = "schools")
    @JsonIgnore
    private Set<Employee> employees;

    @ManyToOne
    @JoinColumn(name="district_id")
    private District district;

    public School(String name, String city,District district) {
        this.name = name;
        this.district=district;
    }

    public School() {
    }

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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void addEmployee(Employee employee)
    {
        this.employees.add(employee);
        employee.getSchools().add(this);
    }

    public void removeEmployee(Employee employee)
    {
//        employees.remove(employee);
        employee.getSchools().remove(this);
    }


}
