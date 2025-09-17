package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.client.ClientDTO;
import com.edumota.minhagaragem.domain.DTO.client.ClientPostDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientPostDTO client, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.insert(userDetails.getId(), client));
    }
}
