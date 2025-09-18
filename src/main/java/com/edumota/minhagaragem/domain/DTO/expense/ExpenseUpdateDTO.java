package com.edumota.minhagaragem.domain.DTO.expense;

import com.edumota.minhagaragem.domain.enums.ExpenseCategory;

import java.math.BigDecimal;

public record ExpenseUpdateDTO(BigDecimal price, String description, ExpenseCategory expenseCategory) {
}
