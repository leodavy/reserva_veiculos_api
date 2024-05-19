package com.curso.reservaveiculosapi.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtils {
    String gerarToken(UserDetails userDetails, JwtPayload jwtPayLoad);
    Jws<Claims> validarToken(String token);
}
