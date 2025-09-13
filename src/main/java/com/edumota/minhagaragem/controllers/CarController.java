package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.car.CarDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarPostDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarUpdateDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpensePostDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseUpdateDTO;
import com.edumota.minhagaragem.services.CarService;
import com.edumota.minhagaragem.services.ExpenseService;
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

    private final CarService carService;

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<Page<CarDTO>> findMyCars(
            Authentication authentication, Pageable pageable,
            @RequestParam(required = false) String brand, @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear, @RequestParam(required = false) Integer maxYear)
    {
        return ResponseEntity.ok().body(carService.findMyCars(UUID.fromString(authentication.getName()), pageable, brand, model, minYear, maxYear));
    }

    @PostMapping
    public ResponseEntity<CarDTO> insert(@RequestBody CarPostDTO car, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.insert(UUID.fromString(authentication.getName()), car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        carService.delete(id, UUID.fromString(authentication.getName()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable Long id, @RequestBody CarUpdateDTO car, Authentication authentication) {
        return ResponseEntity.ok().body(carService.update(id, car, UUID.fromString(authentication.getName())));
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<ExpenseDTO> insertMyExpenseByCar(@RequestBody ExpensePostDTO newExpense,@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.insertMyExpenseByCar(newExpense, id, UUID.fromString(authentication.getName())));
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<Page<ExpenseDTO>> findMyExpensesByCar(@PathVariable Long id, Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(expenseService.findMyExpensesByCar(id, UUID.fromString(authentication.getName()), pageable));
    }
}
