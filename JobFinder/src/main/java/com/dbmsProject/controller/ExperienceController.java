package com.dbmsProject.controller;

import com.dbmsProject.domain.Experience;
import com.dbmsProject.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;
    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }
    @GetMapping("/getExperiences")
    public List<Experience> getExperiences()
    {
        return experienceService.getExperiences();
    }
    @PostMapping("/addExperience/{id}")
    public void addExperience(@PathVariable("id") Long id,@RequestBody Experience experience)
    {
        experienceService.addExperience(experience,id);
    }

    @DeleteMapping("/delete")
    public void removeExperience(@RequestBody Long id)
    {
        experienceService.deleteExperience(id);
    }

    @PutMapping("/update/{id}")
    public void updateExperience(@PathVariable("id") Long id,@RequestBody Experience experience)
    {
        experienceService.updateExperience(id,experience);
    }


}
