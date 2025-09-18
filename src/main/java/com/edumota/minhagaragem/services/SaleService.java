package com.edumota.minhagaragem.services;

import com.edumota.minhagaragem.domain.DTO.sale.SaleDTO;
import com.edumota.minhagaragem.domain.DTO.sale.SalePostDTO;
import com.edumota.minhagaragem.domain.entities.Client;
import com.edumota.minhagaragem.domain.entities.Sale;
import com.edumota.minhagaragem.exceptions.ResourceNotFoundException;
import com.edumota.minhagaragem.repositories.CarRepository;
import com.edumota.minhagaragem.repositories.ClientRepository;
import com.edumota.minhagaragem.repositories.SaleRepository;
import com.edumota.minhagaragem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public SaleDTO insert(SalePostDTO sale, UUID userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        var client = clientRepository.findById(sale.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        var car = carRepository.findById(sale.carId())
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado"));

        return new SaleDTO(saleRepository.save(new Sale(sale.price(), sale.status(), user, client, car)));
    }
}
