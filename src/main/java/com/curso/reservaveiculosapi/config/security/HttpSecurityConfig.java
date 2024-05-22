package com.curso.reservaveiculosapi.config.security;

import com.curso.reservaveiculosapi.config.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class HttpSecurityConfig {
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/primeiro/mms").permitAll()
                            .requestMatchers(HttpMethod.POST, "/usuario", "/usuario/autenticar").permitAll()
                            .requestMatchers(HttpMethod.POST, "/usuario", "/usuario/cadastrar").permitAll()
                            .requestMatchers(HttpMethod.POST,"/usuario/**").authenticated()
                            .requestMatchers(HttpMethod.GET,"/usuario/**").authenticated()
                            .requestMatchers(HttpMethod.GET,"/veiculo/**").authenticated()
                            .requestMatchers(HttpMethod.POST,"/veiculo/**").authenticated()

                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
                            .requestMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                            .anyRequest().authenticated();
                });
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
