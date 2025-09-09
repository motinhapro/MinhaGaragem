package com.edumota.minhagaragem.domain.DTO.finance;

import com.edumota.minhagaragem.domain.enums.ExpenseType;

import java.math.BigDecimal;

public record SpendingByCategoryDTO(ExpenseType type, BigDecimal totalAmount) {
}
