package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioPerfilKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfilEntity, UsuarioPerfilKey> {
    @Query("SELECT up FROM UsuarioPerfilEntity up WHERE up.uspUsuarioPerfilKey.perNrId = :perNrId")
    List<UsuarioPerfilEntity> findAllUsuariosAssociados(long perNrId);
}
