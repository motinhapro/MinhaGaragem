package com.edumota.minhagaragem.config;

import com.edumota.minhagaragem.domain.User;
import com.edumota.minhagaragem.repositories.RoleRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration
@Order(2)
public class AdminUserConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByUsername("admin");

        if(userAdmin.isEmpty()) {

            var role = roleRepository.findByName("ROLE_ADMIN").
                    orElseThrow(() -> new RuntimeException("Role não encontrada."));

            userRepository.save(new User("admin", "admin@gmail.com", passwordEncoder.encode("123"), role));
        }
    }
}
