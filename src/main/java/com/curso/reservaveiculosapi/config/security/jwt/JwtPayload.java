package com.curso.reservaveiculosapi.config.security.jwt;

import java.util.List;

public record JwtPayload(
        Long usuNrId,
        String usuTxNome,
        List<String>roles
) {
}
