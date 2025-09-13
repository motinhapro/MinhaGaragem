package com.edumota.minhagaragem.repositories.specification;

import com.edumota.minhagaragem.domain.Expense;
import com.edumota.minhagaragem.domain.enums.ExpenseType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class ExpenseSpecifications {

    public static Specification<Expense> belongsToUser(UUID userId) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("car").get("user").get(("id")), userId));
    }

    public static Specification<Expense> hasType(ExpenseType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Expense> belongsToCar(Long carId) {
        return (root, query, criteriaBuilder) -> {
            if (carId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("car").get("id"), carId);
        };
    }

    public static Specification<Expense> isBetweenDates(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("expenseDate"), startDate, endDate);
            }

            if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("expenseDate"), startDate);
            }

            if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("expenseDate"), endDate);
            }

            return criteriaBuilder.conjunction();
        };
    }
}
