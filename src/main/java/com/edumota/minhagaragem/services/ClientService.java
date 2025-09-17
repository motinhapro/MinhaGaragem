package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.client.ClientDTO;
import com.edumota.minhagaragem.domain.DTO.client.ClientPostDTO;
import com.edumota.minhagaragem.domain.entities.Client;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.ClientRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    public ClientDTO insert(UUID id, ClientPostDTO client) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        return new ClientDTO(clientRepository.save(new Client(client.name(), client.email(), client.number(), user)));
    }
}
