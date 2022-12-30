package com.dbmsProject.services;

import com.dbmsProject.domain.Employee;
import com.dbmsProject.domain.Experience;
import com.dbmsProject.domain.Kullanici;
import com.dbmsProject.repositories.ExperienceRepository;
import com.dbmsProject.repositories.KullaniciRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final KullaniciRepository<Employee> employeeKullaniciRepository;
    private final KullaniciRepository kullaniciRepository;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository, KullaniciRepository<Employee> employeeKullaniciRepository,
                             KullaniciRepository kullaniciRepository) {
        this.experienceRepository = experienceRepository;
        this.employeeKullaniciRepository = employeeKullaniciRepository;
        this.kullaniciRepository = kullaniciRepository;
    }
    public List<Experience> getExperiences()
    {
        return  experienceRepository.findAll();
    }

    public void addExperience(Experience experienceData, Long employeeId) {
        Optional<Employee> employeeOptional = employeeKullaniciRepository.findById(employeeId);
        Set<Experience> experiences=employeeOptional.get().getExperiences();
        Employee employee = employeeOptional.get();
        experienceData.setEmployee(employee);
        experienceRepository.save(experienceData);
    }

    public void deleteExperience(Long id)
    {
        Optional<Experience> experienceOptional=experienceRepository.findById(id);
        if(experienceOptional.isPresent())
        {
            experienceRepository.delete(experienceOptional.get());
        }
        else
        {
            throw new IllegalStateException("Experience doesn't found!");
        }
    }

    @Transactional
    public void updateExperience(Long id, Experience experienceData)
    {
        Optional<Experience> experienceOptional=experienceRepository.findById(id);
        if(experienceOptional.isPresent())
        {
            Experience experience=experienceOptional.get();
            if(experienceData.getTitle()!=null)
            {
                experience.setTitle(experienceData.getTitle());
            }
            if(experienceData.getBeginDate()!=null)
            {
                experience.setBeginDate(experienceData.getBeginDate());
            }
            if(experienceData.getEndDate()!=null)
            {
                experience.setEndDate(experienceData.getEndDate());
            }
            experienceRepository.save(experience);
        }
    }


}
