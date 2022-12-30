package com.dbmsProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class UserImage {
    @Id
    @Column(name="user_id")
    private Long id;
    private String path;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @MapsId
    @JsonIgnore
    private Kullanici user;
    public UserImage() {
    }

    public UserImage(Long id, String path, Kullanici user) {
        this.id = id;
        this.path = path;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kullanici getUser() {
        return user;
    }

    public void setUser(Kullanici user) {
        this.user = user;
    }

    public UserImage(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
