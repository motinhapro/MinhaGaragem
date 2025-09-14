package com.edumota.minhagaragem.domain.DTO.expense;

import com.edumota.minhagaragem.domain.entities.Car;
import com.edumota.minhagaragem.domain.entities.Expense;
import com.edumota.minhagaragem.domain.enums.ExpenseType;

import java.math.BigDecimal;

public record ExpenseDTO(BigDecimal price, String description, ExpenseType expenseType, Car car) {

    public ExpenseDTO(Expense expense) {
        this(expense.getPrice(), expense.getDescription(), expense.getExpenseType(), expense.getCar());
    }
}
