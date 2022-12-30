package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Employer extends Kullanici{

    @OneToMany(cascade= CascadeType.ALL,mappedBy="employer")
    private Set<JobAdvertisement> jobAdvertisements;

    public Set<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public void setJobAdvertisements(Set<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }
    @ManyToMany
    @JoinTable(name="company_employer",
              joinColumns=@JoinColumn(name="employer_id"),
            inverseJoinColumns=@JoinColumn(name="company_id"))
    @JsonIgnoreProperties("employers")
    private Set<Company> companies;

    public Employer(Long id, String username, String name, String surname,String email,
                    String password, LocalDate dob, KullaniciType userType,
                    UserImage userImage, Set<JobAdvertisement> jobAdvertisements) {
        super(id, username, name, surname,email, password, dob, userType, userImage);
        this.jobAdvertisements = jobAdvertisements;
    }

    public Employer(Long id, String username, String name, String surname,String email, String password,
                    LocalDate dob, KullaniciType userType, UserImage userImage) {
        super(id, username, name, surname,email, password, dob, userType, userImage);
    }
    public Employer()
    {

    }


    public Employer(Set<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
    public void addCompany(Company company)
    {
        this.companies.add(company);
        company.getEmployers().add(this);
    }


}
