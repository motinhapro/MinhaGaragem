package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.CarRepository;
import com.edumota.minhagaragem.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CarRepository carRepository;

    public Page<ExpenseDTO> findMyExpensesByCar(Long id, UUID userId, Pageable pageable) {

        var car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        return expenseRepository.findByCar(car, pageable).map(ExpenseDTO::new);
    }
}
