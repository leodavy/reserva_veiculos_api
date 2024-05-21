package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfilEntity, UsuarioPerfilKey> {

}
