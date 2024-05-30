package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilEntity;
import com.curso.reservaveiculosapi.repository.PerfilRepository;
import com.curso.reservaveiculosapi.repository.UsuarioPerfilRepository;
import com.curso.reservaveiculosapi.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioPerfilRepository usuarioPerfilRepository;


    public PerfilEntity criarPerfil(String nome) {
        if(perfilRepository.findByPerTxNome(nome).isPresent()){
            throw new IllegalArgumentException("Esse usuNrNome já existe!");
        }
        System.out.println("criando perfil: " + nome);
        PerfilEntity perfil = PerfilEntity.builder().perTxNome(nome).build();
        return perfilRepository.save(perfil);
    }

    public List<PerfilEntity> getAllPerfis() {
        return perfilRepository.findAll();
    }
    public Optional<PerfilEntity> getPerfilById(long perNrId) {
        return perfilRepository.findById(perNrId);
    }
    public long getTotalPerfis() {
        return perfilRepository.countPerfis();
    }
    public List<UsuarioPerfilEntity> getAllUsuariosAssociados(long perNrId) {
        return usuarioPerfilRepository.findAllUsuariosAssociados(perNrId);
    }


}
