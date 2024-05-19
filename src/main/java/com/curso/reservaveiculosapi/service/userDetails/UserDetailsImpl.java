package com.curso.reservaveiculosapi.service.userDetails;

import com.curso.reservaveiculosapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String usuTxLogin) throws UsernameNotFoundException {
        return (UserDetails) this.usuarioRepository.findUsuarioEntitiesByUsuTxLoginLikeIgnoreCase(usuTxLogin)
                .orElseThrow();
    }
}
