package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByCar(Car car, Pageable pageable);
}
