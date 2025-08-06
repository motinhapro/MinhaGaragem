package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.Expense;
import com.edumota.minhagaragem.domain.dto.ExpensePostDTO;
import com.edumota.minhagaragem.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public Expense insert(ExpensePostDTO expense) {
        return repository.save(new Expense(null, expense.price(), expense.description(), expense.type()));
    }
}
