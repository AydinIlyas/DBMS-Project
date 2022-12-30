package com.dbmsProject.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=15)
    private String name;
    @OneToMany(cascade=CascadeType.ALL,mappedBy ="city" )
    private Set<District> districts;

    public City(Integer id, String name, Set<District> districts) {
        this.id = id;
        this.name = name;
        this.districts = districts;
    }
    public City()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
