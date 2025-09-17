package com.edumota.minhagaragem.domain.DTO.sale;

import com.edumota.minhagaragem.domain.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record SaleDTO(
        UUID saleId,
        String clientName,
        String carDescription,
        BigDecimal price,
        SaleStatus status,
        Instant createdAt) {
}
