package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class District {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30,nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="city_id",nullable = false)
    @JsonIgnoreProperties("districts")
    private City city;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "district")
    @JsonIgnore
    private Set<JobAdvertisement> jobAdvertisement;

    public District(Integer id, String name, City city, Set<JobAdvertisement> jobAdvertisement) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.jobAdvertisement = jobAdvertisement;
    }
    public District()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<JobAdvertisement> getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(Set<JobAdvertisement> jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }
}
