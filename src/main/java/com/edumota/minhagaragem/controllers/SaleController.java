package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.sale.SaleDTO;
import com.edumota.minhagaragem.domain.DTO.sale.SalePostDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleDTO> insert(@RequestBody SalePostDTO sale, @AuthenticationPrincipal User userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insert(sale, userDetails.getId()));
    }
}
