package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.services.ExpenseService;
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
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> findMyExpenses(Authentication authentication, Pageable pageable) {
        return ResponseEntity.ok().body(service.findMyExpenses(UUID.fromString(authentication.getName()), pageable));
    }

}
