package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    Optional<PerfilEntity> findByPerTxNome(String perTxNome);

    @Query("SELECT COUNT(p) FROM PerfilEntity p")
    long countPerfis();

    Optional<PerfilEntity> findById(Long perNrId);
}
