package com.edumota.minhagaragem.domain.DTO;

import com.edumota.minhagaragem.domain.Car;

public record CarDTO(String model, String brand, int year, String colour) {

    public CarDTO(Car car) {
        this(car.getModel(), car.getBrand(), car.getYear(), car.getColour());
    }
}
