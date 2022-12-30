package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class JobAdvertisementImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    @ManyToOne
    @JsonIgnore
    private JobAdvertisement jobAdvertisement;

    public JobAdvertisementImage(Long id, String path, JobAdvertisement jobAdvertisement) {
        this.id = id;
        this.path = path;
        this.jobAdvertisement = jobAdvertisement;
    }
    public JobAdvertisementImage()
    {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
        jobAdvertisement.getJobAdvertisementImages().add(this);
    }
}
