package com.dbmsProject.controller;

import com.dbmsProject.domain.Position;
import com.dbmsProject.repositories.KullaniciRepository;
import com.dbmsProject.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    private final KullaniciRepository kullaniciRepository;

    @Autowired
    public PositionController(PositionService positionService,
                              KullaniciRepository kullaniciRepository) {
        this.positionService = positionService;
        this.kullaniciRepository = kullaniciRepository;
    }

    @GetMapping("/get")
    public List<Position> getPositions()
    {
         return positionService.getPositions();
    }

    @PostMapping("/add")
    public void addPosition(@RequestBody Position position)
    {
        positionService.addPosition(position);
    }

    @DeleteMapping("/delete")
    public void deletePosition(@RequestBody Long id)
    {
        positionService.deletePosition(id);
    }


}
