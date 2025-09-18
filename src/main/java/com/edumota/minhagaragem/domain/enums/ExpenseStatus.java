package com.edumota.minhagaragem.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseStatus {
    PENDING("Pendente"),
    PAID("Pago"),
    CANCELED("Cancelado");

    private final String displayName;
}
