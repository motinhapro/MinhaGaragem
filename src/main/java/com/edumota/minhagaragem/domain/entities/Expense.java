package com.edumota.minhagaragem.domain.entities;

import com.edumota.minhagaragem.domain.enums.ExpenseCategory;
import com.edumota.minhagaragem.domain.enums.ExpenseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_expenses")
public class Expense extends BaseEntity {

    @Column(nullable = false, precision = 10, scale = 2)
    @Positive(message = "O preço deve ser maior que zero.")
    @DecimalMax(value = "1000000.00", message = "O preço não pode exceder R$ 1.000.000,00")
    private BigDecimal amount;

    @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseCategory category;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseStatus status;

    public Expense(BigDecimal amount, ExpenseCategory expenseCategory, String description, Car car, ExpenseStatus status) {
        this.amount = amount;
        this.category = expenseCategory;
        this.description = description;
        this.car = car;
        this.status = status != null ? status : ExpenseStatus.PENDING;
    }
}
