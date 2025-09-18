package com.edumota.minhagaragem.domain.DTO.finance;

import com.edumota.minhagaragem.domain.enums.ExpenseCategory;

import java.math.BigDecimal;

public record SpendingByCategoryDTO(ExpenseCategory type, BigDecimal totalAmount) {
}
