package com.curso.reservaveiculosapi.config.security.jwt.impl;

import com.curso.reservaveiculosapi.config.security.jwt.JwtPayload;
import com.curso.reservaveiculosapi.config.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtilsImpl implements JwtUtils {
    @Value("${config.jwt.secret}")
    private String secret;
    @Value("${config.jwt.expiration}")
    private Long expiration;
    @Override
    public String gerarToken(UserDetails userDetails, JwtPayload jwtPayLoad) {
        Key secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA512");
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuer("RESERVA_VEIICULOS_API")
                .header()
                .add("typ", "JWT")
                .and()
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .claim("payload", jwtPayLoad)
                .compact();
    }
    @Override
    public Jws<Claims> validarToken(String token) {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA512");
        var jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
        return jwtParser.parseSignedClaims(token);
    }
}
