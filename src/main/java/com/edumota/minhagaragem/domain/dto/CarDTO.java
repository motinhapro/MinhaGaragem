package com.edumota.minhagaragem.domain.dto;

import com.edumota.minhagaragem.domain.Car;

import java.time.LocalDateTime;

public record CarDTO(String name, String year, String colour, LocalDateTime createdAt) {

    public CarDTO(Car car) {
        this(car.getName(), car.getYear(), car.getColour(), car.getCreatedAt());
    }
}
