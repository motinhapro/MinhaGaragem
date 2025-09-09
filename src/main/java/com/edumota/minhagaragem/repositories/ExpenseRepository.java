package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByCar(Car car, Pageable pageable);

    @Query("SELECT e FROM Expense where e.car.user.id = :userId")
    Page<Expense> findByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT SUM(e.price) FROM Expense e WHERE e.car.user.id = :userId")
    BigDecimal findTotalSpendingByUserId(@Param("userId") UUID userId);
}
