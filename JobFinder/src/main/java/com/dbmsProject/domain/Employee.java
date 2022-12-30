package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends Kullanici {
    @ManyToMany
    @JoinTable(name="employee_school",
            joinColumns=@JoinColumn(name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="school_id"))
    private Set<School> schools;

    @ManyToMany
    @JoinTable(name="position_employee",
            joinColumns=@JoinColumn(name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="position_id"))
    private Set<Position> positions;

    @OneToMany(cascade=CascadeType.ALL,mappedBy="employee")
    private Set<Experience> experiences;

    @ManyToMany
    @JoinTable(name="Application",
        joinColumns = @JoinColumn(name="employee_id"),
        inverseJoinColumns = @JoinColumn(name="job_id"))
    private Set<JobAdvertisement> jobAdvertisements;

    public Employee(Long id, String username, String name, String surname,String email,
                    String password, LocalDate dob, KullaniciType userType, UserImage userImage,
                    Set<School> schools, Set<Position> positions, Set<Experience> experiences,
                    Set<JobAdvertisement> jobAdvertisements) {
        super(id, username, name, surname,email, password, dob, userType, userImage);
        this.schools = schools;
        this.positions = positions;
        this.experiences = experiences;
        this.jobAdvertisements = jobAdvertisements;
    }

    public Employee(Set<School> schools, Set<Position> positions, Set<Experience> experiences, Set<JobAdvertisement> jobAdvertisements) {
        this.schools = schools;
        this.positions = positions;
        this.experiences = experiences;
        this.jobAdvertisements = jobAdvertisements;
    }

    public Employee(Long id, String username, String name, String surname,String email,
                    String password, LocalDate dob, KullaniciType userType, UserImage userImage) {
        super(id, username, name, surname,email, password, dob, userType, userImage);
    }
    public Employee()
    {

    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;

        for(Experience b : experiences) {
            b.setEmployee(this);
        }
    }
    public Set<School> getSchools() {
        return schools;
    }

    public void setSchools(Set<School> schools) {
        this.schools = schools;
    }
    public void addSchool(School school)
    {
        this.schools.add(school);
        school.getEmployees().add(this);
    }
    public void removeSchool(School school)
    {
        schools.remove(school);
        school.getEmployees().remove(this);
    }



    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
    public void addPosition(Position position)
    {
        this.positions.add(position);
        position.getEmployees().add(this);
    }

    public Set<JobAdvertisement> getJobAdvertisements() {
        return jobAdvertisements;
    }

    public void setJobAdvertisements(Set<JobAdvertisement> jobAdvertisements) {
        this.jobAdvertisements = jobAdvertisements;
    }
}
