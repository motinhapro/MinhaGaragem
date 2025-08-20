package com.edumota.minhagaragem.domain.DTO.expense;

import com.edumota.minhagaragem.domain.enums.ExpenseType;

import java.math.BigDecimal;

public record ExpensePostDTO(BigDecimal price, String description, ExpenseType expenseType) {
}
