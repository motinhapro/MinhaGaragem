package com.edumota.minhagaragem.domain.DTO.expense;

import com.edumota.minhagaragem.domain.enums.ExpenseCategory;

import java.time.LocalDate;

public record ExpenseSearchFilterDTO(
        Long carId,
        ExpenseCategory type,
        LocalDate expenseDateStart,
        LocalDate expenseDateEnd
) {
}
