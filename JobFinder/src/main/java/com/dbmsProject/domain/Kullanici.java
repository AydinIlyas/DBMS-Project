package com.dbmsProject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Kullanici {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,nullable = false)
    private String username;
    @Column(length=30, nullable = false)
    private String name;
    @Column(length=30, nullable = false)
    private String surname;
    @Column(length=55,nullable = false,unique = true)
    private String email;
    @Column(length=30, nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate dob;

    private Integer age=0;
    private LocalDate creationDate=LocalDate.of(2000,01,01);
    @Enumerated(value = EnumType.STRING)
    private KullaniciType userType;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy="user")
    @PrimaryKeyJoinColumn
    private UserImage userImage;

    public Kullanici(Long id, String username, String name, String surname,String email,
                     String password, LocalDate dob, KullaniciType userType, UserImage userImage) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email=email;
        this.password = password;
        this.dob = dob;
        this.userType = userType;
        this.userImage = userImage;
    }

    public Kullanici() {
    }

    public KullaniciType getUserType() {
        return userType;
    }

    public void setUserType(KullaniciType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
