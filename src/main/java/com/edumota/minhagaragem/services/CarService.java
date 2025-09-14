package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.DTO.car.CarDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarPostDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarUpdateDTO;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.CarRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import com.edumota.minhagaragem.repositories.specification.CarSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    public Page<CarDTO> findMyCars(UUID id, Pageable pageable, String brand, String model, Integer minYear, Integer maxYear, LocalDate startDate, LocalDate endDate) {

        Specification<Car> baseSpec = CarSpecifications.belongsToUser(id);

        Specification<Car> finalSpec = baseSpec
                .and(CarSpecifications.hasBrand(brand))
                .and(CarSpecifications.modelLike(model))
                .and(CarSpecifications.byYearRange(minYear, maxYear))
                .and(CarSpecifications.acquiredBetweenDate(startDate, endDate));

        return carRepository.findAll(finalSpec, pageable).map(CarDTO::new);
    }

    public CarDTO insert(UUID id, CarPostDTO car) {

        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado."));

        return new CarDTO(carRepository.save(new Car(car.model(), car.brand(), car.year(), car.colour(), user)));
    }

    public void delete(Long id, UUID userId) {

        var car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        carRepository.deleteById(id);
    }

    public CarDTO update(Long id, CarUpdateDTO newCar, UUID userId) {

        var car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        car.setModel(newCar.model());
        car.setBrand(newCar.brand());
        car.setYear(newCar.year());
        car.setColour(newCar.colour());

        return new CarDTO(carRepository.save(car));
    }
}
