package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.dto.ExpenseDTO;
import com.edumota.minhagaragem.domain.dto.ExpensePostDTO;
import com.edumota.minhagaragem.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/expenses")
public class ExpenseController {

    private final ExpenseService service;

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(ExpenseDTO::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpenseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ExpenseDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> insert(@RequestBody ExpensePostDTO expense) {
        return ResponseEntity.ok().body(new ExpenseDTO(service.insert(expense)));
    }
}
