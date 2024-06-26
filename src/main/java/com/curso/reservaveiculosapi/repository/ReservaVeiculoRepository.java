package com.curso.reservaveiculosapi.repository;

import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaVeiculoRepository extends JpaRepository<ReservaVeiculoEntity, Long> {
    void deleteByVeiNrId(long veiNrId);

    List<ReservaVeiculoEntity> findByUsuNrId(long usuNrId);
}
