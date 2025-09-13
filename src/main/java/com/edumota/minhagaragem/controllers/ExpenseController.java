package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<Page<ExpenseDTO>> findMyExpenses(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(expenseService.findMyExpenses(UUID.fromString(authentication.getName()), pageable));
    }
}
