package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.expense.ExpenseDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpensePostDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseSearchFilterDTO;
import com.edumota.minhagaragem.domain.DTO.expense.ExpenseUpdateDTO;
import com.edumota.minhagaragem.domain.entities.Expense;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.CarRepository;
import com.edumota.minhagaragem.repositories.ExpenseRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import com.edumota.minhagaragem.repositories.specification.ExpenseSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    public Page<ExpenseDTO> findMyExpenses(UUID userId, Pageable pageable, ExpenseSearchFilterDTO filters) {

        Specification<Expense> baseSpec = ExpenseSpecifications.belongsToUser(userId);

        Specification<Expense> finalSpec = baseSpec
                .and(ExpenseSpecifications.belongsToCar(filters.carId()))
                .and(ExpenseSpecifications.hasType(filters.type()))
                .and(ExpenseSpecifications.isBetweenDates(filters.expenseDateStart(), filters.expenseDateEnd()));

        return expenseRepository.findAll(finalSpec, pageable).map(ExpenseDTO::new);
    }

    public ExpenseDTO insertMyExpenseByCar(ExpensePostDTO newExpense, Long carId, UUID userId) {

        var car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        return new ExpenseDTO(expenseRepository.save(new Expense(newExpense.price(), newExpense.expenseCategory(), newExpense.description(), car)));
    }

    public Page<ExpenseDTO> findMyExpensesByCar(Long carId, UUID userId, Pageable pageable) {

        var car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado."));

        if(!car.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário deste veículo.");
        }

        return expenseRepository.findByCar(car, pageable).map(ExpenseDTO::new);
    }

    public void deleteMyExpense(UUID userId, Long expenseId) {

        var expense = expenseRepository.findById(expenseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada."));

        if(!expense.getCar().getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário desta despesa.");
        }

        expenseRepository.deleteById(expenseId);
    }

    public ExpenseDTO updateMyExpense(UUID userId, Long expenseId, ExpenseUpdateDTO dto) {

        var expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada."));

        if(!expense.getCar().getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário desta despesa.");
        }

        expense.setPrice(dto.price());
        expense.setDescription(dto.description());
        expense.setExpenseCategory(dto.expenseCategory());

        return new ExpenseDTO(expenseRepository.save(expense));
    }
}
