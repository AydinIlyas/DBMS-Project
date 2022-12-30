package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.apache.catalina.valves.rewrite.RewriteCond;

import java.util.Set;


@Entity
public class JobAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    @JsonIgnoreProperties("jobAdvertisements")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(nullable = false)
    private Float brutMaas;

    private Float netMaas=0.0f;

    @ManyToOne
    @JoinColumn(name="district_id",nullable=true)
    @JsonIgnoreProperties("jobAdvertisement")
    private District district;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="jobAdvertisement")
    private Set<JobAdvertisementImage> jobAdvertisementImages;

    @OneToMany(cascade=CascadeType.ALL,mappedBy="jobAdvertisement")
    private Set<Requirement> requirements;

    @ManyToMany(cascade=CascadeType.REMOVE,mappedBy = "jobAdvertisements")
    @JsonIgnore
    private Set<Employee> employees;

    public JobAdvertisement(Long id, Employer employer, Position position,
                            District district, Set<JobAdvertisementImage> jobAdvertisementImages,
                            Set<Requirement> requirements, Set<Employee> employees,Float brutMaas) {
        this.id = id;
        this.employer = employer;
        this.position = position;
        this.district = district;
        this.jobAdvertisementImages = jobAdvertisementImages;
        this.requirements = requirements;
        this.employees = employees;
        this.brutMaas=brutMaas;
    }

    public JobAdvertisement()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        if(employer.getJobAdvertisements().add(this));
        {this.employer = employer;}

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }


    public void setJobAdvertisementImages(Set<JobAdvertisementImage> jobAdvertisementImages) {
        this.jobAdvertisementImages = jobAdvertisementImages;
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;

        for(Requirement b : requirements) {
            b.setJobAdvertisement(this);
        }
    }
    public void removeJobAdvertisement(Employee employee)
    {
        employee.getJobAdvertisements().remove(this);
        employees.remove(employee);
    }
    public void remove()
    {
        for(Employee emp:employees)
        {
            if(emp.getJobAdvertisements().contains(this))
            {
                employees.remove(emp);
            }
        }
    }
    public Set<JobAdvertisementImage> getJobAdvertisementImages() {
        return jobAdvertisementImages;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public void add(Employee employee)
    {
        employee.getJobAdvertisements().add(this);
        employees.add(employee);
    }

    public Float getBrutMaas() {
        return brutMaas;
    }

    public void setBrutMaas(Float brutMaas) {
        this.brutMaas = brutMaas;
    }

    public Float getNetMaas() {
        return netMaas;
    }

    public void setNetMaas(Float netMaas) {
        this.netMaas = netMaas;
    }
}
