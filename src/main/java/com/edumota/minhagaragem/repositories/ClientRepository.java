package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
