package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
