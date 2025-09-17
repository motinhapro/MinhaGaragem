package com.edumota.minhagaragem.domain.DTO.client;

import com.edumota.minhagaragem.domain.entities.Client;

public record ClientDTO(String name, String number) {

    public ClientDTO(Client client) {
        this(client.getName(), client.getNumber());
    }
}
