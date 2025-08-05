package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.dto.CarDTO;
import com.edumota.minhagaragem.domain.dto.CarPostDTO;
import com.edumota.minhagaragem.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(CarDTO::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CarDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CarDTO> insert(@RequestBody CarPostDTO car){
        return ResponseEntity.ok().body(new CarDTO(service.insert(car)));
    }
}
