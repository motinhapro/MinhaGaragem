package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.services.ExpenseService;
import com.edumota.minhagaragem.services.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/finances")
public class FinanceController {

    private final FinanceService financeService;

    private final ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<Page<ExpenseDTO>> findMyExpenses(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(expenseService.findMyExpenses(UUID.fromString(authentication.getName()), pageable));
    }
}
