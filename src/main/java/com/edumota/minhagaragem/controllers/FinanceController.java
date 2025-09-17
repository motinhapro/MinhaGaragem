package com.edumota.minhagaragem.controllers;

import com.edumota.minhagaragem.domain.DTO.finance.SpendingByCategoryDTO;
import com.edumota.minhagaragem.domain.DTO.finance.TotalSpendingDTO;
import com.edumota.minhagaragem.domain.entities.User;
import com.edumota.minhagaragem.services.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/finances")
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping("/summary/total")
    public ResponseEntity<TotalSpendingDTO> getTotalSpending(
            @AuthenticationPrincipal User userDetails,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(financeService.getTotalSpending(userDetails.getId(), startDate, endDate));
    }

    @GetMapping("/summary/by-category")
    public ResponseEntity<List<SpendingByCategoryDTO>> getSpendingByType(@AuthenticationPrincipal User userDetails) {
        return ResponseEntity.ok(financeService.getSpendingByType(userDetails.getId()));
    }
}
