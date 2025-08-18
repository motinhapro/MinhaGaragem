package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.AuthResponseDTO;
import com.edumota.minhagaragem.domain.DTO.LoginRequestDTO;
import com.edumota.minhagaragem.domain.DTO.RegisterRequestDTO;
import com.edumota.minhagaragem.domain.User;
import com.edumota.minhagaragem.repositories.RoleRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthResponseDTO login(LoginRequestDTO request) {

        var user = userRepository.findByEmail(request.email());

        if(user.isEmpty() || !passwordEncoder.matches(request.password(), user.get().getPassword())) {
            throw new BadCredentialsException("Usuário ou senha inválidos.");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new AuthResponseDTO(jwtValue);
    }

    public void register(RegisterRequestDTO request) {

        var user = userRepository.findByEmail(request.email());

        if(user.isPresent()) {
            throw new BadCredentialsException("Usuário já existe.");
        }

        var role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userRepository.save(new User(request.username(), request.email(), passwordEncoder.encode(request.password()), role));
    }
}
