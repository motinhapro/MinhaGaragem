package com.edumota.minhagaragem.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseCategory {
    MECANICA("Mecânica"),
    DOCUMENTO("Documento"),
    VISTORIA("Vistoria"),
    PINTURA("Pintura"),
    FUNILARIA("Funilaria"),
    TROCADEOLEO("Troca de óleo"),
    SEGURO("Seguro"),
    OUTRO("Outro");

    private final String displayName;
}
