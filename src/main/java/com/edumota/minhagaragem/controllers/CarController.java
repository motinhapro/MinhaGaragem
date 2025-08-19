package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.CarDTO;
import com.edumota.minhagaragem.domain.DTO.CarPostDTO;
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
}
