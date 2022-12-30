package com.dbmsProject.services;

import com.dbmsProject.domain.Company;
import com.dbmsProject.domain.District;
import com.dbmsProject.repositories.CompanyRepository;
import com.dbmsProject.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository,
                          DistrictRepository districtRepository) {
        this.companyRepository = companyRepository;
        this.districtRepository = districtRepository;
    }

    public List<Company> getCompanies() {
        List<Company> list=new ArrayList<>();
        companyRepository.findAll().forEach(list::add);
        return list;
    }
    public void addCompany(Company company)
    {
        if(!companyRepository.existsByNameAndSince(company.getName(),company.getSince()))
        {
            Optional<District> districtOptional=districtRepository.
                    findByNameAndCity_Id(company.getLocation().getName(),company.getLocation().getCity().getId());
            company.setLocation(districtOptional.get());
            companyRepository.save(company);
        }
        else{
            throw new IllegalStateException("Company already exists!");
        }
    }


}
