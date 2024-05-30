package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    PerfilEntity criarPerfil(String nome);
    List<PerfilEntity> getAllPerfis();
    Optional<PerfilEntity> getPerfilById(long perNrId);
    public long getTotalPerfis();

}
