package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.client.ClientDTO;
import com.edumota.minhagaragem.domain.DTO.client.ClientPostDTO;
import com.edumota.minhagaragem.domain.entities.Client;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.ClientRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import com.edumota.minhagaragem.repositories.specification.ClientSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    public ClientDTO insert(UUID userId, ClientPostDTO client) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        return new ClientDTO(clientRepository.save(new Client(client.name(), client.email(), client.number(), user)));
    }

    public Page<ClientDTO> findMyClients(UUID userId, Pageable pageable, String name) {

        Specification<Client> baseSpec = ClientSpecifications.belongsToUser(userId);

        Specification<Client> finalSpec = baseSpec
                .and(ClientSpecifications.withNameLike(name));

        return clientRepository.findAll(finalSpec, pageable).map(ClientDTO::new);
    }

    public void delete(Long clientId, UUID userId) {

        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        if(!client.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Acesso negado. Você não é proprietário desse cliente");
        }

        clientRepository.deleteById(clientId);
    }
}
