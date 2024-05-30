package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilEntity;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    PerfilEntity criarPerfil(String nome);
    List<PerfilEntity> getAllPerfis();
    Optional<PerfilEntity> getPerfilById(long perNrId);
    long getTotalPerfis();
    List<UsuarioPerfilEntity> getAllUsuariosAssociados(long perNrId);

}
