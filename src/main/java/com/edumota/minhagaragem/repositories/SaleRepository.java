package com.edumota.minhagaragem.repositories;

import com.edumota.minhagaragem.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
