package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.finance.SpendingByCategoryDTO;
import com.edumota.minhagaragem.domain.DTO.finance.TotalSpendingDTO;
import com.edumota.minhagaragem.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FinanceService {

    private final ExpenseRepository expenseRepository;

    public TotalSpendingDTO getTotalSpending(UUID userId, LocalDate StartDate, LocalDate endDate) {
        BigDecimal total = expenseRepository.findTotalSpendingByUserAndPeriod(userId, StartDate, endDate)
                .orElse(BigDecimal.ZERO);

        return new TotalSpendingDTO(total);
    }

    public List<SpendingByCategoryDTO> getSpendingByType(UUID userId) {
        return expenseRepository.findSpendingGroupedByType(userId);
    }
}
