package com.edumota.minhagaragem.domain.DTO.expense;

import com.edumota.minhagaragem.domain.enums.ExpenseType;

import java.time.LocalDate;

public record ExpenseSearchFilterDTO(
        Long carId,
        ExpenseType type,
        LocalDate expenseDateStart,
        LocalDate expenseDateEnd
) {
}
