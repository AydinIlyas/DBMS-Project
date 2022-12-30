package com.dbmsProject.controller;

import com.dbmsProject.domain.JobAdvertisement;
import com.dbmsProject.domain.JobAdvertisementImage;
import com.dbmsProject.domain.Requirement;
import com.dbmsProject.repositories.JobAdvertisementRepository;
import com.dbmsProject.repositories.RequirementRepostitory;
import com.dbmsProject.services.JobAdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Advertisement")
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;
    private final RequirementRepostitory requirementRepostitory;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService,
                                      RequirementRepostitory requirementRepostitory,
                                      JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementService = jobAdvertisementService;
        this.requirementRepostitory = requirementRepostitory;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }
    @GetMapping("/get")
    public List<JobAdvertisement> getJobAds()
    {
        return jobAdvertisementService.getJobAdvertisements();
    }

    @PostMapping("/add/{id}")
    public void addJobAdvertisement(@PathVariable("id") Long id, @RequestBody JobAdvertisement job)
    {
        jobAdvertisementService.addJobAdvertisement(id,job);
    }
    @PostMapping("/addImage/{id}")
    public void addJobAdImage(@PathVariable("id")Long id, @RequestBody JobAdvertisementImage image)
    {
        jobAdvertisementService.addImage(id,image);

    }
    @PostMapping("/addRequirement/{id}")
    public void addRequirement(@PathVariable("id") Long id,@RequestBody Requirement requirement)
    {
        jobAdvertisementService.addRequirement(id,requirement);
    }

    @DeleteMapping("/delete")
    public void deleteAd(@RequestBody Long id)
    {
        jobAdvertisementService.deleteAd(id);
    }

    @DeleteMapping("/{ad_id}/deleteRequirement")
    public void deleteAdRequirement(@PathVariable("ad_id") Long ad_id,@RequestBody Long requirement_id)
    {
        jobAdvertisementService.deleteRequirement(ad_id,requirement_id);
    }



}
