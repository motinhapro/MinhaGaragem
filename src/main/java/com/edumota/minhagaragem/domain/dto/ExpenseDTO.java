package com.edumota.minhagaragem.domain.dto;

import com.edumota.minhagaragem.domain.Expense;

import java.time.LocalDateTime;

public record ExpenseDTO(Double price, String description, Expense.ExpenseType type, LocalDateTime createdAt) {

    public ExpenseDTO(Expense expense) {
        this(expense.getPrice(), expense.getDescription(), expense.getType(), expense.getCreatedAt());
    }
}
