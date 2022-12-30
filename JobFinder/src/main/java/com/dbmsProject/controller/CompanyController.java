package com.dbmsProject.controller;

import com.dbmsProject.domain.Company;
import com.dbmsProject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping("/get")
    public List<Company> getCompanies()
    {
        return companyService.getCompanies();
    }
    @PostMapping("/add")
    public void addCompany(@RequestBody Company company)
    {
        companyService.addCompany(company);
    }


}
