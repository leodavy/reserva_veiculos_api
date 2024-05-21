package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.config.security.jwt.JwtPayload;
import com.curso.reservaveiculosapi.config.security.jwt.JwtUtils;

import com.curso.reservaveiculosapi.exceptions.PasswordException;
import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilKey;
import com.curso.reservaveiculosapi.model.forms.AutenticacaoForm;
import com.curso.reservaveiculosapi.model.forms.UsuarioForm;
import com.curso.reservaveiculosapi.repository.PerfilRepository;
import com.curso.reservaveiculosapi.repository.UsuarioPerfilRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioPerfilRepository usuarioPerfilRepository;
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
                .roles(List.of("ROLE_USER"))
                .build();
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return usuarioEntity.toDTO();
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioEntity> listaUsuarios = this.usuarioRepository.findAll();
        return listaUsuarios.stream()
                .map(UsuarioEntity::toDTO)
                .collect(Collectors.toList());
    }
    public void associarPerfilUsuario(Long usuNrId, Long perNrId) {
//        UsuarioPerfilEntity usuarioPerfilEntity = new UsuarioPerfilEntity();
        UsuarioPerfilKey key = new UsuarioPerfilKey(usuNrId, perNrId);
        UsuarioPerfilEntity usuarioPerfilEntity = new UsuarioPerfilEntity();
        usuarioPerfilEntity.setUspUsuarioPerfilKey(key);
        usuarioPerfilRepository.save(usuarioPerfilEntity);
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
        return this.jwtUtils.gerarToken(usuarioEntity, jwtPayLoad);
    }

}
