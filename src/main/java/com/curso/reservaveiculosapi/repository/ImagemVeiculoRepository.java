package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.ImagemVeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemVeiculoRepository extends JpaRepository<ImagemVeiculoEntity, Long> {
    List<ImagemVeiculoEntity> findByVeiNrId(Long veiNrId);
    void deleteByVeiNrId(long veiNrId);
}
