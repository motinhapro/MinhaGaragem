package com.edumota.minhagaragem.domain.DTO.sale;

import com.edumota.minhagaragem.domain.enums.SaleStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record SalePostDTO(
        @NotNull
        UUID clientId,
        @NotNull
        UUID carId,
        @NotNull(message = "O preço de venda é obrigatório.")
        BigDecimal price,
        @NotNull(message = "O status da venda é obrigatório")
        SaleStatus status){
}
