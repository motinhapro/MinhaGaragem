package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseUpdateDTO;
import com.edumota.minhagaragem.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<Page<ExpenseDTO>> findMyExpenses(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(expenseService.findMyExpenses(UUID.fromString(authentication.getName()), pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(Authentication authentication,  @PathVariable Long id, @RequestBody ExpenseUpdateDTO dto) {
        return ResponseEntity.ok().body(expenseService.updateMyExpense(UUID.fromString(authentication.getName()), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(Authentication authentication, @PathVariable Long id) {
        expenseService.deleteMyExpense(UUID.fromString(authentication.getName()), id);
        return ResponseEntity.noContent().build();
    }

}
