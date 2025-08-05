package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
