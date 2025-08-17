package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.AuthResponseDTO;
import com.edumota.minhagaragem.domain.DTO.LoginRequestDTO;
import com.edumota.minhagaragem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok().body(service.login(request));
    }
}
