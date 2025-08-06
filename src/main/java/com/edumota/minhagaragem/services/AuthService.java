package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.controllers.dto.LoginRequest;
import com.edumota.minhagaragem.controllers.dto.LoginResponse;
import com.edumota.minhagaragem.controllers.dto.RegisterRequest;
import com.edumota.minhagaragem.domain.Role;
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
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public LoginResponse login(LoginRequest request) {
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Username or password invalid"));

        if (!user.isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("Username or password invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(token, expiresIn);
    }

    public User register(RegisterRequest request) {

        var userDb = userRepository.findByUsername(request.username());

        if(userDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userRepository.save(
                new User(request.username(), request.password(), Set.of(roleRepository.findByName(Role.Values.BASIC.name())))
        );
    }
}
