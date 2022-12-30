package com.dbmsProject.services;

import com.dbmsProject.domain.*;
import com.dbmsProject.repositories.*;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.*;

@Service
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciRepository<Employee> employeeKullaniciRepository;
    private final KullaniciRepository<Employer> employerKullaniciRepository;
    private final ExperienceRepository experienceRepository;
    private final SchoolRepository schoolRepository;
    private final PositionRepository positionRepository;
    private final UserImageRepository userImageRepository;
    private final CompanyRepository companyRepository;
    private final DistrictRepository districtRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public KullaniciService(KullaniciRepository kullaniciRepository, KullaniciRepository<Employee> employeeKullaniciRepository, KullaniciRepository<Employer> employerKullaniciRepository,
                            ExperienceRepository experienceRepository,
                            SchoolRepository schoolRepository,
                            PositionRepository positionRepository,
                            UserImageRepository userImageRepository,
                            CompanyRepository companyRepository,
                            DistrictRepository districtRepository,
                            JobAdvertisementRepository jobAdvertisementRepository) {
        this.kullaniciRepository = kullaniciRepository;
        this.employeeKullaniciRepository = employeeKullaniciRepository;
        this.employerKullaniciRepository = employerKullaniciRepository;
        this.experienceRepository = experienceRepository;
        this.schoolRepository = schoolRepository;
        this.positionRepository = positionRepository;
        this.userImageRepository = userImageRepository;
        this.companyRepository = companyRepository;
        this.districtRepository = districtRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public List<Kullanici> getUsers()
    {
        return kullaniciRepository.findAllByOrderByIdAsc();
    }

    public List<Employee> getEmployee(){
        return employeeKullaniciRepository.findEmployee();
    }
    public List<Employer> getEmployer(){
        return employerKullaniciRepository.findEmployer();
    }
    public void addNewEmployee(Employee employee)
    {
        String name=employee.getUsername();
        name=name.toLowerCase();
        name=name.trim();
        if(employeeKullaniciRepository.existsByName(name))
        {
            throw new IllegalStateException("This username is already taken!");
        }
        Optional<Employee>employeeOptional=employeeKullaniciRepository.findEmployeeByUserName(employee.getUsername());
        if(!employeeOptional.isPresent())
        {
            employee.setUserType(KullaniciType.Employee);
            //kullaniciRepository.save(employee);
            employeeKullaniciRepository.save(employee);
        }
        else{
            throw new IllegalStateException("This username is already taken!");
        }

    }
    public void addNewEmployer(Employer employer)
    {
        String name=employer.getUsername();
        name=name.toLowerCase();
        name=name.trim();
        if(employerKullaniciRepository.existsByName(name))
        {
            throw new IllegalStateException("This username is already taken!");
        }
        Optional<Employer> employerOptional=employerKullaniciRepository.findEmployerByUserName(employer.getUsername());
        if(!employerOptional.isPresent())
        {
            if(employer.getUserImage()!=null)
                employer.getUserImage().setUser(employer);
            employer.setUserType(KullaniciType.Employer);
            //kullaniciRepository.save(employer);
            employerKullaniciRepository.save(employer);
        }
        else{
            throw new IllegalStateException("This username is already taken");
        }

    }

    @Transactional
    public void updateUser(Long id,Kullanici kullaniciData) {
        Optional<Kullanici> kullaniciOptional=kullaniciRepository.findById(id);
        if(kullaniciOptional.isPresent())
        {
            Kullanici kullanici=kullaniciOptional.get();
            if(kullaniciData.getName()!=null&& !Objects.equals(kullaniciData.getName(),kullanici.getName()))
            {
                kullanici.setName(kullaniciData.getName());
            }
            if(kullaniciData.getUsername()!=null&& !Objects.equals(kullaniciData.getUsername(),kullanici.getUsername()))
            {
                kullanici.setUsername(kullaniciData.getUsername());
            }
            if(kullaniciData.getPassword()!=null&& !Objects.equals(kullaniciData.getPassword(),kullanici.getPassword()))
            {
                kullanici.setPassword(kullaniciData.getPassword());
            }
            if(kullaniciData.getSurname()!=null&&!Objects.equals(kullaniciData.getSurname(),kullanici.getSurname()))
            {
                kullanici.setSurname(kullaniciData.getSurname());
            }
            if(kullaniciData.getEmail()!=null&&!Objects.equals(kullaniciData.getEmail(),kullanici.getEmail()))
            {
                kullanici.setEmail(kullaniciData.getEmail());
            }
            kullaniciRepository.save(kullanici);
        }

    }
    public List<Kullanici> searchUser(String user)
    {
        List<Kullanici> kullaniciList=kullaniciRepository.findAllByUserName(user);
        return kullaniciList;
    }

    public void addSchool(Long id, String name)
    {
        Optional<School> schoolOptional=schoolRepository.findByName(name);
        Optional<Employee> kullaniciOptional=kullaniciRepository.findEmployeeById(id);
        Set<School> schools=kullaniciOptional.get().getSchools();
        if(schoolOptional.isPresent())
        {
            for(School school:schools)
            {
                if(school.equals(schoolOptional.get()))
                {
                    throw new IllegalStateException("This school is already added");
                }
            }
            schoolOptional.get().addEmployee(kullaniciOptional.get());
        }
        else{
            throw new IllegalStateException("This school doesn't exist");
        }
        kullaniciRepository.save(kullaniciOptional.get());
        schoolRepository.save(schoolOptional.get());
    }

    public void addPositions(Long id,String name)
    {
        Optional<Employee> employeeOptional=kullaniciRepository.findById(id);
        Set<Position> positions=employeeOptional.get().getPositions();
        Optional<Position> positionOptional=positionRepository.findByName(name);
        if(positionOptional.isPresent())
        {
            if(positions.add(positionOptional.get()))
            {
                positionRepository.save(positionOptional.get());
            }
            else{
                throw new IllegalStateException("Position is already added!");
            }
        }
        else{
            throw new IllegalStateException("Position doesn't exist!");
        }
    }
    public void addImage(Long id,UserImage image)
    {
        Optional<Kullanici>user=kullaniciRepository.findById(id);
        image.setUser(user.get());
        user.get().setUserImage(image);
        userImageRepository.save(image);
    }


    public void addCompany(Long employerId,Long companyId)
    {
        Optional<Employer> employerOptional=kullaniciRepository.findEmployerById(employerId);
        Optional<Company> companyOptional=companyRepository.findById(companyId);
        if(employerOptional==null)
        {
            throw new IllegalStateException("Employer doesn't exist");
        }
        if(companyOptional==null)
        {
            throw new IllegalStateException("Company doesn't exist");
        }
        Employer employer =employerOptional.get();
        employer.addCompany(companyOptional.get());
        kullaniciRepository.save(employer);
    }
    public List<District> getDistricts()
    {
        List<District> districts=new ArrayList<>();
        districtRepository.findAll().forEach(districts::add);
        return districts;
    }

    public void removeUser(Long id)
    {
        Optional<Kullanici> userOptional=kullaniciRepository.findById(id);
        if(userOptional.isPresent())
        {
            kullaniciRepository.delete(userOptional.get());
        }
        else{
            throw new IllegalStateException("User not found!");
        }
    }

    public void apply(Long employee_id, Long Ad_id)
    {
        Optional<Employee> employeeOptional=kullaniciRepository.findEmployeeById(employee_id);
        Optional<JobAdvertisement> jobAdvertisementOptional= jobAdvertisementRepository.findById(Ad_id);
        if(!employeeOptional.isPresent())
        {
            throw new IllegalStateException("Employee doesn't exist!");
        }
        if(!jobAdvertisementOptional.isPresent())
        {
            throw new IllegalStateException("Job Ad doesn't exist!");
        }
        jobAdvertisementOptional.get().add(employeeOptional.get());

        employeeKullaniciRepository.save(employeeOptional.get());
    }

    public void deleteUserImage(Long id)
    {
        Optional<Kullanici>kullaniciOptional= kullaniciRepository.findById(id);
        if(kullaniciOptional.isPresent())
        {
            if(userImageRepository.existsById(id))
            {
                Optional<UserImage> imageOptional=userImageRepository.findById(id);
                kullaniciOptional.get().setUserImage(null);
                imageOptional.get().setUser(null);
                userImageRepository.delete(imageOptional.get());
            }
            else{
                throw new IllegalStateException("User has no profil picture!");
            }
        }
        else{
            throw new IllegalStateException("User not found!");
        }
    }
    public void deleteRelationtoSchool(Long employee_id,Long school_id)
    {
        Optional<Employee> employeeOptional=kullaniciRepository.findEmployeeById(employee_id);
        if(!employeeOptional.isPresent())
        {
            throw new IllegalStateException("Employee doesn't exist");
        }
        Optional<School> schoolOptional=schoolRepository.findById(school_id);
        if(!schoolOptional.isPresent())
        {
            throw new IllegalStateException("School doesn't exist");
        }
        if(!schoolOptional.get().getEmployees().contains(employeeOptional.get()))
        {
            throw new IllegalStateException("Employee and school has no relation!");
        }
        schoolOptional.get().removeEmployee(employeeOptional.get());
        kullaniciRepository.save(employeeOptional.get());
    }

    public void deleteExperience(Long employee_id ,Long experience_id)
    {
        Optional<Employee> employeeOptional = kullaniciRepository.findEmployeeById(employee_id);
        if(!employeeOptional.isPresent())
        {
            throw new IllegalStateException("Employee doesn't exist!");
        }
        Optional<Experience> experienceOptional=experienceRepository.findById(experience_id);
        if(!experienceOptional.isPresent())
        {
            throw new IllegalStateException("Experience doesn't exist!");
        }
        if(!employeeOptional.get().getExperiences().contains(experienceOptional.get()))
        {
            throw new IllegalStateException("");
        }
        experienceRepository.delete(experienceOptional.get());
    }
    public List<Experience> getExperiences(Long id)
    {
        return experienceRepository.findAllByEmployee_Id(id);
    }

}
