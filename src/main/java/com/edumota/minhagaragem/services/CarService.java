package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.CarDTO;
import com.edumota.minhagaragem.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public Page<CarDTO> findMyCars(UUID id, Pageable pageable) {

        return carRepository.findByUserId(id, pageable).map(CarDTO::new);
    }
}
