package com.dbmsProject.services;

import com.dbmsProject.domain.Employee;
import com.dbmsProject.domain.JobAdvertisement;
import com.dbmsProject.domain.Position;
import com.dbmsProject.repositories.JobAdvertisementRepository;
import com.dbmsProject.repositories.KullaniciRepository;
import com.dbmsProject.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final KullaniciRepository kullaniciRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository,
                           KullaniciRepository kullaniciRepository,
                           JobAdvertisementRepository jobAdvertisementRepository) {
        this.positionRepository = positionRepository;
        this.kullaniciRepository = kullaniciRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public List<Position> getPositions()
    {
        List<Position> list=new ArrayList<>();
        positionRepository.findAll().forEach(list::add);
        return list;
    }
    public void addPosition(Position position)
    {
        Optional<Position> positionOptional=positionRepository.findByName(position.getName());
        if(!positionOptional.isPresent())
        positionRepository.save(position);
        else{
            throw new IllegalStateException("This position already exists!");
        }
    }
    public void deletePosition(Long id)
    {
        Optional<Position> positionOptional=positionRepository.findById(id);
        if(positionOptional.isPresent())
        {
            Set<Employee> employees=positionOptional.get().getEmployees();
            for(Employee emp: employees)
            {
                positionOptional.get().removeEmployees(emp);
            }
            for(JobAdvertisement job: positionOptional.get().getJobAdvertisements())
            {
                jobAdvertisementRepository.delete(job);
            }


        }
        else{
            throw new IllegalStateException("Position not found!");
        }
        positionRepository.delete(positionOptional.get());
    }
//    public void delete(Long id)
//    {
//        Optional<Position> positionOptional=positionRepository.findById(id);
//
//        if(positionOptional.isPresent())
//        {
//            Set<Employee> employees=positionOptional.get().getEmployees();
//            for(Employee employee : employees)
//            {
//                positionOptional.get().removeEmployees(employee);
//                kullaniciRepository.save(employee);
//            }
//            positionRepository.delete(positionOptional.get());
//        }
//    }



}
