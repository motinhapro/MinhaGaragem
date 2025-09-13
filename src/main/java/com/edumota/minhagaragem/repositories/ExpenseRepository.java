package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.Car;
import com.edumota.minhagaragem.domain.DTO.finance.SpendingByCategoryDTO;
import com.edumota.minhagaragem.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByCar(Car car, Pageable pageable);

    Page<Expense> findByCar_User_Id(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT new com.edumota.minhagaragem.domain.DTO.finance.SpendingByCategoryDTO(e.expenseType, SUM(e.price)) " +
            "FROM Expense e WHERE e.car.user.id = :userId " +
            "GROUP BY e.expenseType " +
            "ORDER BY SUM(e.price) DESC")
    List<SpendingByCategoryDTO> findSpendingGroupedByType(@Param("userId") UUID userId);

    @Query("SELECT sum(e.price) from Expense e WHERE e.car.user.id = :userId " +
            "AND (cast(:startDate as date) IS NULL OR e.createdAt >= :startDate) " +
            "AND (cast(:endDate as date) IS NULL OR e.createdAt <= :endDate)")
    Optional<BigDecimal> findTotalSpendingByUserAndPeriod(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
