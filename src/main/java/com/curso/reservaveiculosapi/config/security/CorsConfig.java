package com.curso.reservaveiculosapi.config.security;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders(HttpHeaders.AUTHORIZATION)
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
