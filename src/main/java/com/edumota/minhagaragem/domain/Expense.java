package com.edumota.minhagaragem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "tb_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    private Double price;
    private String description;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Expense(Long id, Double price, String description, ExpenseType type) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    public enum ExpenseType {

        PARTS(1L),
        DOCUMENTS(2L),
        PAINTING(3L),
        BODYWORK(4L),
        MECHANICAL(5L),
        MAINTENANCE(6L),
        ELECTRICAL(7L);

        private final long expenseTypeId;

        ExpenseType(long expenseTypeId) {
            this.expenseTypeId = expenseTypeId;
        }
    }
}
