package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.car.CarDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarPostDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarUpdateDTO;
import com.edumota.minhagaragem.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    @GetMapping
    public ResponseEntity<Page<CarDTO>> findMyCars(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(service.findMyCars(UUID.fromString(authentication.getName()), pageable));
    }

    @PostMapping
    public ResponseEntity<CarDTO> insert(@RequestBody CarPostDTO car, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(UUID.fromString(authentication.getName()), car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        service.delete(id, UUID.fromString(authentication.getName()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarUpdateDTO car, Authentication authentication) {
        return ResponseEntity.ok().body(service.update(id, car, UUID.fromString(authentication.getName())));
    }
}
