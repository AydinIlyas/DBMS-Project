package com.dbmsProject.services;

import com.dbmsProject.domain.District;
import com.dbmsProject.domain.Employee;
import com.dbmsProject.domain.Kullanici;
import com.dbmsProject.domain.School;
import com.dbmsProject.repositories.DistrictRepository;
import com.dbmsProject.repositories.KullaniciRepository;
import com.dbmsProject.repositories.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SchoolService{
    private final SchoolRepository schoolRepository;
    private final KullaniciRepository kullaniciRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository,
                         KullaniciRepository kullaniciRepository,
                         DistrictRepository districtRepository) {
        this.schoolRepository = schoolRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.districtRepository = districtRepository;
    }

    public List<School> getSchools()
    {
        return schoolRepository.findAll();
    }
    public void addNewSchool(School school)
    {
        if(school.getName()==null)
        {
            throw new IllegalStateException("School name required");
        }
        Optional<School> schoolOptional=schoolRepository.findByName(school.getName());
        if(!schoolOptional.isPresent())
        {
            if(school.getDistrict()!=null)
            {Optional<District> districtOptional = districtRepository.
                    findByNameAndCity_Id(school.getDistrict().getName(),school.getDistrict().getCity().getId());
            if(!districtOptional.isPresent())
            {
                throw new IllegalStateException("District doesn't exist in Turkey");
            }
            school.setDistrict(districtOptional.get());}
            schoolRepository.save(school);
        }
        else{
            throw new IllegalStateException("This school already exists!");
        }
    }
    @Transactional
    public void updateSchool(Long id,School schoolData)
    {
        Optional<School> schoolOptional=schoolRepository.findById(id);
        if(schoolOptional.isPresent())
        {
            School school=schoolOptional.get();
            if(schoolData.getName()!=null)
            {
                school.setName(schoolData.getName());
            }
            if(schoolData.getDistrict()!=null)
            {
                Optional<District> districtOptional = districtRepository.
                        findByNameAndCity_Id(schoolData.getDistrict().getName(),schoolData.getDistrict().getCity().getId());
                if(!districtOptional.isPresent())
                {
                    throw new IllegalStateException("District doesn't exist in Turkey");
                }
                school.setDistrict(districtOptional.get());
            }
        schoolRepository.save(school);
        }
    }

    public void deleteSchool(Long id)
    {
        Optional<School> schoolOptional=schoolRepository.findById(id);

        if(schoolOptional.isPresent())
        {
            School school=schoolOptional.get();
            for(Employee employee:school.getEmployees())
            {
                school.removeEmployee(employee);
                kullaniciRepository.save(employee);
            }
        }
        else{
            throw new IllegalStateException("School not found!");
        }
        schoolRepository.delete(schoolOptional.get());
    }
}
