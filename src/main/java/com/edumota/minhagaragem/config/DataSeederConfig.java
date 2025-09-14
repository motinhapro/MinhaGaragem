package com.edumota.minhagaragem.config;

import com.edumota.minhagaragem.domain.entities.Car;
import com.edumota.minhagaragem.domain.entities.Expense;
import com.edumota.minhagaragem.domain.entities.Role;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.domain.enums.ExpenseType;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.CarRepository;
import com.edumota.minhagaragem.repositories.ExpenseRepository;
import com.edumota.minhagaragem.repositories.RoleRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Configuration
@Order(1)
@Profile("dev")
public class DataSeederConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ExpenseRepository expenseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if(roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }

        if(roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }

        if(userRepository.count() == 0) {
            User testUser = new User();

            var role = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new ResourceNotFoundException("Role não pode ser encontrada."));

            testUser.setUsername("testuser");
            testUser.setEmail("test@email.com");
            testUser.setPassword(passwordEncoder.encode("123456"));
            testUser.setRole(role);
            userRepository.save(testUser);

            Car car1 = new Car("Civic", "Honda", 2023, "Prata", testUser);
            Car car2 = new Car("Corolla", "Toyota", 2022, "Preto", testUser);
            Car car3 = new Car("HB20", "Hyundai", 2024, "Branco", testUser);
            carRepository.saveAll(List.of(car1, car2, car3));

            expenseRepository.saveAll(List.of(
                    new Expense(new BigDecimal("2800.00"), ExpenseType.DOCUMENTO, "Transferência", car1),
                    new Expense(new BigDecimal("350.00"), ExpenseType.DOCUMENTO, "retrovisor", car1),
                    new Expense(new BigDecimal("120.00"), ExpenseType.MECANICA, "Troca de óleo", car1)
            ));

            expenseRepository.saveAll(List.of(
                    new Expense(new BigDecimal("2800.00"), ExpenseType.PINTURA, "Pintura", car2),
                    new Expense(new BigDecimal("3500.00"), ExpenseType.MECANICA, "Parachoque", car2),
                    new Expense(new BigDecimal("120.00"), ExpenseType.MECANICA, "Fusível", car2)
            ));

            expenseRepository.saveAll(List.of(
                    new Expense(new BigDecimal("3500.00"), ExpenseType.PINTURA, "Pintura", car3),
                    new Expense(new BigDecimal("1500.00"), ExpenseType.DOCUMENTO, "IPVA", car3)
            ));

            System.out.println(">>> Banco de dados populado com sucesso!");
        }
    }
}
