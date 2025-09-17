package com.edumota.minhagaragem.domain.DTO.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientPostDTO(

        @NotBlank(message = "O nome do cliente não pode estar em branco.")
        String name,

        @NotBlank(message = "O número do cliente não pode estar em branco.")
        String number,

        @Email(message = "O formato do email é inválido")
        String email) {
}
