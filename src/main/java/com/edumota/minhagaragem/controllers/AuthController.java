package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.auth.AuthResponseDTO;
import com.edumota.minhagaragem.domain.DTO.auth.LoginRequestDTO;
import com.edumota.minhagaragem.domain.DTO.auth.RegisterRequestDTO;
import com.edumota.minhagaragem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO request) {
        service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
