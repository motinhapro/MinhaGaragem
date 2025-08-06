package com.edumota.minhagaragem.config;

import com.edumota.minhagaragem.domain.Role;
import com.edumota.minhagaragem.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(1)
public class DataInitializerConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(">>> Inserindo roles...");

        if (!roleRepository.existsById(Role.Values.ADMIN.getRoleId())) {
            var adminRole = new Role();
            adminRole.setId(Role.Values.ADMIN.getRoleId());
            adminRole.setName(Role.Values.ADMIN.name());
            roleRepository.save(adminRole);
        }

        if (!roleRepository.existsById(Role.Values.BASIC.getRoleId())) {
            var basicRole = new Role();
            basicRole.setId(Role.Values.BASIC.getRoleId());
            basicRole.setName(Role.Values.BASIC.name());
            roleRepository.save(basicRole);
        }
    }
}
