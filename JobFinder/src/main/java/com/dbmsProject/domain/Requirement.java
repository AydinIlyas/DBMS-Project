package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Requirement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=50)
    private String title;

    @Column(columnDefinition="TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name="job_id")
    @JsonIgnore
    private JobAdvertisement jobAdvertisement;

    public Requirement(Long id, String title, String content, JobAdvertisement jobAdvertisement) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.jobAdvertisement = jobAdvertisement;
    }
    public Requirement() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }

    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        jobAdvertisement.getRequirements().add(this);
        this.jobAdvertisement = jobAdvertisement;
    }
}
