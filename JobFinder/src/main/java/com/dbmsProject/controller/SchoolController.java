package com.dbmsProject.controller;

import com.dbmsProject.domain.Kullanici;
import com.dbmsProject.domain.School;
import com.dbmsProject.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;
    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @GetMapping("/getSchool")
    public List<School> getSchools(){return schoolService.getSchools();}

    @PostMapping("/addSchool")
    public void addUser(@RequestBody School school)
    {
        schoolService.addNewSchool(school);
    }

    @DeleteMapping("/delete")
    public void removeSchool(@RequestBody Long id)
    {
        schoolService.deleteSchool(id);
    }
    @PutMapping("/update/{id}")
    public void updateSchool(@PathVariable("id") Long id,@RequestBody School school)
    {
        schoolService.updateSchool(id,school);
    }
}
