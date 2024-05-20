package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.repository.PerfilRepository;
import com.curso.reservaveiculosapi.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Primary
@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    @Override
    public PerfilEntity criarPerfil(String nome) {
        if(perfilRepository.findByPerTxNome(nome).isPresent()){
            throw new IllegalArgumentException("Esse nome já existe!");
        }
        PerfilEntity perfil = PerfilEntity.builder()
                .perTxNome(nome)
                .build();
        return perfilRepository.save(perfil);
    }

    public List<PerfilEntity> getAllPerfis() {
        return perfilRepository.findAll();
    }
    public Optional<PerfilEntity> getPerfilById(long perNrId) {
        return perfilRepository.findById(perNrId);
    }

}
