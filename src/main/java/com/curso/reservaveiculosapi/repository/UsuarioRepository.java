package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findUsuarioEntitiesByUsuTxLoginLikeIgnoreCase(String usuTxLogin);


}
