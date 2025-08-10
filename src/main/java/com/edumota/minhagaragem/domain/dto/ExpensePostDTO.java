package com.edumota.minhagaragem.domain.dto;

import com.edumota.minhagaragem.domain.Expense;

public record ExpensePostDTO(Double price, String description, Expense.ExpenseType type) {
}
