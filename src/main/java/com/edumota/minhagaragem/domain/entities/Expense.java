package com.edumota.minhagaragem.domain.entities;

import com.edumota.minhagaragem.domain.enums.ExpenseType;
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

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @Positive(message = "O preço deve ser maior que zero.")
    @DecimalMax(value = "1000000.00", message = "O preço não pode exceder R$ 1.000.000,00")
    private BigDecimal price;

    @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ExpenseType expenseType;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Expense(BigDecimal price, ExpenseType expenseType, String description, Car car) {
        this.price = price;
        this.expenseType = expenseType;
        this.description = description;
        this.car = car;
    }
}
