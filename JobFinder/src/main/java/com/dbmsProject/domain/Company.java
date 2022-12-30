package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 55,nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District location;
    @Column(nullable = false)
    private LocalDate since;
    @ManyToMany(mappedBy = "companies")
    @JsonIgnoreProperties("companies")
    //@JsonBackReference
    private Set<Employer> employers;

    public Company()
    {

    }
    public Company(Long id, String name, District location, LocalDate since, Set<Employer> employers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.since = since;
        this.employers = employers;
    }

    public District getLocation() {
        return location;
    }

    public void setLocation(District location) {
        this.location = location;
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

    public LocalDate getSince() {
        return since;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }

    public Set<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<Employer> employers) {
        this.employers = employers;
    }
}
