package com.curso.reservaveiculosapi.config.security.jwt;

public record JwtPayload(
        Long id,
        String nome
) {
}
