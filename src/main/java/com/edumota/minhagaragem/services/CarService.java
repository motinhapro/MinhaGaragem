package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.dto.CarPostDTO;
import com.edumota.minhagaragem.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Car findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Car insert(CarPostDTO car) {
        return repository.save(new Car(null, car.name(), car.year(), car.colour()));
    }
}
