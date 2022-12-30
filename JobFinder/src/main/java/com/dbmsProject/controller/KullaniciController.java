package com.dbmsProject.controller;

import com.dbmsProject.domain.*;
import com.dbmsProject.repositories.ExperienceRepository;
import com.dbmsProject.repositories.KullaniciRepository;
import com.dbmsProject.services.KullaniciService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class KullaniciController {

    private final KullaniciService kullaniciService;
    private final KullaniciRepository kullaniciRepository;
    private final ExperienceRepository experienceRepository;

    @Autowired
    public KullaniciController(KullaniciService kullaniciService,
                               KullaniciRepository kullaniciRepository,
                               ExperienceRepository experienceRepository) {
        this.kullaniciService = kullaniciService;
        this.kullaniciRepository = kullaniciRepository;
        this.experienceRepository = experienceRepository;
    }

    @GetMapping("/getUser")
    public List<Kullanici> getUser(){return kullaniciService.getUsers();}

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees(){return kullaniciService.getEmployee();}

    @GetMapping("/getEmployers")
    public List<Employer> getEmployers(){return kullaniciService.getEmployer();}

    @PostMapping("/addEmployee")
    public void addUser(@RequestBody Employee kullanici)
    {
        kullaniciService.addNewEmployee(kullanici);
    }
    @PostMapping("/addEmployer")
    public void addUser(@RequestBody Employer kullanici)
    {
        kullaniciService.addNewEmployer(kullanici);
    }
    @PostMapping("/addSchool/{id}")
    public void addSchool(@PathVariable("id")Long id,@RequestBody String name)
    {
        kullaniciService.addSchool(id,name);
    }
    @PostMapping("/addPosition/{id}")
    public void addExperience(@PathVariable("id")Long id,@RequestBody String name)
    {
        kullaniciService.addPositions(id,name);
    }
    @PostMapping("/addImage/{id}")
    public void addImage(@PathVariable("id") Long id,@RequestBody UserImage image)
    {
        kullaniciService.addImage(id,image);
    }
    @PostMapping("/addCompany/{id}")
    public void addCompanyToEmployer(@PathVariable("id") Long employerId,@RequestBody Long companyId)
    {
        kullaniciService.addCompany(employerId,companyId);
    }
    @GetMapping("/districts")
    public List<District> getDistricts()
    {
        return kullaniciService.getDistricts();
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Long id)
    {
        kullaniciService.removeUser(id);
    }

    @PostMapping("/apply/{user}")
    public void apply(@PathVariable("user") Long user_id,@RequestBody Long ad_id )
    {
        kullaniciService.apply(user_id,ad_id);
    }
    @DeleteMapping("/deleteImage")
    public void deleteImage(@RequestBody Long id)
    {
        kullaniciService.deleteUserImage(id);
    }
    @DeleteMapping("/deleteSchool/{id}")
    public void deleteRelation(@PathVariable("id") Long employee_id,@RequestBody Long school_id)
    {
        kullaniciService.deleteRelationtoSchool(employee_id,school_id);
    }

    @GetMapping("/getExperiences/{id}")
    public List<Experience> getExperiences(@PathVariable("id") Long employee_id)
    {
        return kullaniciService.getExperiences(employee_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExperience(@PathVariable("id") Long employee_id,@RequestBody Long experience_id)
    {
        kullaniciService.deleteExperience(employee_id,experience_id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable("id") Long user_id,@RequestBody Kullanici kullanici)
    {
        kullaniciService.updateUser(user_id,kullanici);
    }
    @GetMapping("/search")
    public List<Kullanici> searchUser(@RequestBody String username)
    {
        return kullaniciService.searchUser(username);
    }





}