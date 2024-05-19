package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.ImagemVeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemVeiculoRepository extends JpaRepository<ImagemVeiculoEntity, Long> {
}
