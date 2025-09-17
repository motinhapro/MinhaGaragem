package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseSearchFilterDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseUpdateDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<Page<ExpenseDTO>> findMyExpenses(
            @AuthenticationPrincipal User userDetails, Pageable pageable,
            ExpenseSearchFilterDTO filters) {
        return ResponseEntity.ok(expenseService.findMyExpenses(userDetails.getId(), pageable, filters));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@AuthenticationPrincipal User userDetails, @PathVariable Long id, @RequestBody ExpenseUpdateDTO dto) {
        return ResponseEntity.ok(expenseService.updateMyExpense(userDetails.getId(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@AuthenticationPrincipal User userDetails, @PathVariable Long id) {
        expenseService.deleteMyExpense(userDetails.getId(), id);
        return ResponseEntity.noContent().build();
    }
}