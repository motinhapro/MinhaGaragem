package com.edumota.minhagaragem.domain.DTO.car;

import com.edumota.minhagaragem.domain.Car;

import java.time.Instant;

public record CarDTO(String model, String brand, int year, String colour, Instant createdAt) {

    public CarDTO(Car car) {
        this(car.getModel(), car.getBrand(), car.getYear(), car.getColour(), car.getCreatedAt());
    }
}
