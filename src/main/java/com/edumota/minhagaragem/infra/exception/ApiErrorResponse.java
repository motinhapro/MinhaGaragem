package com.edumota.minhagaragem.infra.exception;

public record ApiErrorResponse(
        int statusCode,
        String message,
        String path,
        java.time.Instant timestamp
) {}
