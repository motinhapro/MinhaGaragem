package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FinanceService {

    private final ExpenseRepository expenseRepository;
}
