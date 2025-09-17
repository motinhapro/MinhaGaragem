package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.client.ClientDTO;
import com.edumota.minhagaragem.domain.DTO.client.ClientPostDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientPostDTO client, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.insert(userDetails.getId(), client));
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findMyClients(
            @AuthenticationPrincipal User userDetails,
            Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok().body(clientService.findMyClients(userDetails.getId(), pageable, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User userDetails) {
        clientService.delete(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
