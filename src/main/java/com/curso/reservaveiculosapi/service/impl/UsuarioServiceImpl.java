package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.config.security.jwt.JwtPayload;
import com.curso.reservaveiculosapi.config.security.jwt.JwtUtils;

import com.curso.reservaveiculosapi.exceptions.PasswordException;
import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import com.curso.reservaveiculosapi.model.forms.AutenticacaoForm;
import com.curso.reservaveiculosapi.model.forms.UsuarioForm;
import com.curso.reservaveiculosapi.repository.UsuarioRepository;
import com.curso.reservaveiculosapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UsuarioDTO insert(UsuarioForm usuarioForm) {
        var senhaCrua = usuarioForm.getUsuTxSenha();
        if (senhaCrua.length() < 5) {
            throw new PasswordException("Senha muito curta");
        }
        var senha = passwordEncoder.encode(senhaCrua);
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .usuTxNome(usuarioForm.getUsuTxNome())
                .usuTxLogin(usuarioForm.getUsuTxLogin())
                .usuTxSenha(senha)
                .build();
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return usuarioEntity.toDTO();
    }
    public String autenticar(AutenticacaoForm autenticacaoForm) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(autenticacaoForm.login(), autenticacaoForm.senha()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciais incorretas");
        }
        UsuarioEntity usuarioEntity = this.usuarioRepository.findUsuarioEntitiesByUsuTxLoginLikeIgnoreCase(autenticacaoForm.login())
                .orElseThrow();
        JwtPayload jwtPayLoad = new JwtPayload(usuarioEntity.getUsuNrId(), usuarioEntity.getUsuTxNome());
        return this.jwtUtils.gerarToken((UserDetails) usuarioEntity, jwtPayLoad);
    }

}
