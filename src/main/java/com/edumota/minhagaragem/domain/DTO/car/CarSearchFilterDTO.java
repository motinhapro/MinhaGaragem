package com.edumota.minhagaragem.domain.DTO.car;

import java.time.LocalDate;

public record CarSearchFilterDTO(
        String brand,
        String model,
        Integer minYear,
        Integer maxYear,
        LocalDate acquisitionDateStart,
        LocalDate acquisitionDateEnd
) {
}
