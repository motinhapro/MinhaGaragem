package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.controllers.dto.LoginRequest;
import com.edumota.minhagaragem.controllers.dto.LoginResponse;
import com.edumota.minhagaragem.controllers.dto.RegisterRequest;
import com.edumota.minhagaragem.domain.dto.UserDTO;
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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(new UserDTO(service.register(request)));
    }
}
