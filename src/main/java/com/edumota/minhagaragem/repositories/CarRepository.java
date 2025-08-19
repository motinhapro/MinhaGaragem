package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findByUserId(UUID id, Pageable pageable);
}
