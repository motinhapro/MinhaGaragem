package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseUpdateDTO;
import com.edumota.minhagaragem.exceptions.BadRequestException;
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

    public Page<ExpenseDTO> findMyExpensesByCar(Long carId, UUID userId, Pageable pageable) {

        var car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        return expenseRepository.findByCar(car, pageable).map(ExpenseDTO::new);
    }

    public void deleteMyExpenseByCar(Long carId, Long expenseId, UUID userId) {

        var car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário desta veículo.");
        }

        var expense = expenseRepository.findById(expenseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada."));

        if(!expense.getCar().getId().equals(carId)) {
            throw new BadRequestException("Conflito de dados: A despesa não pertence ao veículo informado");
        }

        expenseRepository.deleteById(expenseId);
    }

    public ExpenseDTO updateMyExpenseByCar(ExpenseUpdateDTO newExpense, Long carId, Long expenseId, UUID userId) {

        var car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário desta veículo.");
        }

        var expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada."));

        if(!expense.getCar().getId().equals(carId)) {
            throw new BadRequestException("Conflito de dados: A despesa não pertence ao veículo informado");
        }

        expense.setPrice(newExpense.price());
        expense.setDescription(newExpense.description());
        expense.setExpenseType(newExpense.expenseType());

        return new ExpenseDTO(expenseRepository.save(expense));
    }
}
