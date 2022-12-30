package com.dbmsProject.services;

import com.dbmsProject.domain.*;
import com.dbmsProject.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobAdvertisementService {
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final KullaniciRepository kullaniciRepository;
    private final KullaniciRepository<Employer> employerRepository;
    private final PositionRepository positionRepository;
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final RequirementRepostitory requirementRepostitory;

    @Autowired
    public JobAdvertisementService(JobAdvertisementRepository jobAdvertisementRepository,
                                   KullaniciRepository kullaniciRepository, KullaniciRepository<Employer> employerRepository,
                                   PositionRepository positionRepository,
                                   DistrictRepository districtRepository,
                                   CityRepository cityRepository,
                                   RequirementRepostitory requirementRepostitory) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.employerRepository =employerRepository;
        this.positionRepository = positionRepository;
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
        this.requirementRepostitory = requirementRepostitory;
    }
    public List<JobAdvertisement> getJobAdvertisements()
    {
        return jobAdvertisementRepository.findAll();
    }
    public void addJobAdvertisement(Long id,JobAdvertisement job)
    {
        Optional<Employer> employerOptional=kullaniciRepository.findEmployerById(id);

        if(employerOptional.isPresent())
        {
            Optional< District> districtOptional=districtRepository
                    .findByNameAndCity_Id(job.getDistrict().getName(),job.getDistrict().getCity().getId());
            Optional<Position> positionOptional=positionRepository.findByName(job.getPosition().getName());
            if(districtOptional.isPresent()&&positionOptional.isPresent())
            {
                job.setEmployer(employerOptional.get());
                job.setPosition(positionOptional.get());
                job.setDistrict(districtOptional.get());
            }
            else{
                throw new IllegalStateException("Position or district doesn't exist!");
            }
        }
        else{
            throw new IllegalStateException("Employer doesn't exist!");
        }
        employerRepository.save(employerOptional.get());

    }
    public void addImage(Long id, JobAdvertisementImage image)
    {
        Optional<JobAdvertisement> jobAdvertisementOptional=jobAdvertisementRepository.findById(id);
        if(jobAdvertisementOptional.isPresent())
        {
            image.setJobAdvertisement(jobAdvertisementOptional.get());
        }
        else{
            throw new IllegalStateException("Job Advertisement couldn't found!");
        }
        jobAdvertisementRepository.save(jobAdvertisementOptional.get());
    }
    public void addRequirement(Long id, Requirement requirement)
    {
        Optional<JobAdvertisement> jobAdvertisementOptional=jobAdvertisementRepository.findById(id);
        if(jobAdvertisementOptional.isPresent())
        {
            requirement.setJobAdvertisement(jobAdvertisementOptional.get());
        }
        else{
            throw new IllegalStateException("JobAd not found!");
        }
        jobAdvertisementRepository.save(jobAdvertisementOptional.get());
    }
    public void deleteRequirement(Long ad_id,Long requirement_id)
    {
        Optional<JobAdvertisement> jobAdvertisementOptional=jobAdvertisementRepository.findById(ad_id);
        if(jobAdvertisementOptional.isPresent())
        {
            Optional<Requirement> requirementOptional=requirementRepostitory.findById(requirement_id);
            if(requirementOptional.isPresent())
            {
                requirementRepostitory.delete(requirementOptional.get());
            }
            else {
                throw new IllegalStateException("Requirement not found!");
            }
        }
        else {
            throw new IllegalStateException("Job Ad not found!");
        }
    }


    public void deleteAd(Long id)
    {
        Optional<JobAdvertisement> jobAdvertisementOptional=jobAdvertisementRepository.findById(id);

        if(jobAdvertisementOptional.isPresent())
        {
            Set<Employee> set=jobAdvertisementOptional.get().getEmployees();
            for(Employee emp:set)
            {
                jobAdvertisementOptional.get().removeJobAdvertisement(emp);
            }
        }
        else{
            throw new IllegalStateException("Job Ad doesn't exist");
        }
        jobAdvertisementRepository.delete(jobAdvertisementOptional.get());
    }

    @Transactional
    public void updateJobAdvertisement(Long id,JobAdvertisement jobAdvertisementData)
    {
        Optional<JobAdvertisement> jobAdvertisementOptional=jobAdvertisementRepository.findById(id);
        if(jobAdvertisementOptional.isPresent())
        {
//            JobAdvertisement jobAdvertisement=jobAdvertisementOptional.get();
//            if(jobAdvertisementData.get)
        }
    }


}
