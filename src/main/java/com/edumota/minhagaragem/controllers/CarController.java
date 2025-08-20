package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.car.CarDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarPostDTO;
import com.edumota.minhagaragem.domain.DTO.car.CarUpdateDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
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
    public ResponseEntity<Page<CarDTO>> findMyCars(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(carService.findMyCars(UUID.fromString(authentication.getName()), pageable));
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

    @GetMapping("/{id}/expenses")
    public ResponseEntity<Page<ExpenseDTO>> findMyExpensesByCar(@PathVariable Long id, Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(expenseService.findMyExpensesByCar(id, UUID.fromString(authentication.getName()), pageable));
    }

    @DeleteMapping("/{id}/expenses/{expenseId}")
    public ResponseEntity<Void> deleteMyExpenseByCar(@PathVariable Long id, @PathVariable Long expenseId, Authentication authentication) {
        expenseService.deleteMyExpenseByCar(id, expenseId, UUID.fromString(authentication.getName()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/expenses/{expenseId}")
    public ResponseEntity<ExpenseDTO> updateMyExpenseByCar(@RequestBody ExpenseUpdateDTO newExpense, @PathVariable Long id, @PathVariable Long expenseId, Authentication authentication) {
        return ResponseEntity.ok().body(expenseService.updateMyExpenseByCar(newExpense, id, expenseId, UUID.fromString(authentication.getName())));
    }
}
