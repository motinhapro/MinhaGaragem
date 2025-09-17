package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.car.CarDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarPostDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarUpdateDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpensePostDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.CarService;
import com.edumota.minhagaragem.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<Page<CarDTO>> findMyCars(
            @AuthenticationPrincipal User userDetails, Pageable pageable,
            @RequestParam(required = false) String brand, @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear, @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
    {
        return ResponseEntity.ok(carService.findMyCars(userDetails.getId(), pageable, brand, model, minYear, maxYear, startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<CarDTO> insert(@RequestBody CarPostDTO car, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.insert(userDetails.getId(), car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User userDetails) {
        carService.delete(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarUpdateDTO car, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.ok(carService.update(id, car, userDetails.getId()));
    }

    @PostMapping("/{carId}/expenses")
    public ResponseEntity<ExpenseDTO> insertMyExpenseByCar(@RequestBody ExpensePostDTO newExpense, @PathVariable Long carId, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.insertMyExpenseByCar(newExpense, carId, userDetails.getId()));
    }

    @GetMapping("/{carId}/expenses")
    public ResponseEntity<Page<ExpenseDTO>> findMyExpensesByCar(@PathVariable Long carId, @AuthenticationPrincipal User userDetails, Pageable pageable) {
        return ResponseEntity.ok(expenseService.findMyExpensesByCar(carId, userDetails.getId(), pageable));
    }
}
