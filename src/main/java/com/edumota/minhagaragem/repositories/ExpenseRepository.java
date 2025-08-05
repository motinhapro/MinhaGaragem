package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
