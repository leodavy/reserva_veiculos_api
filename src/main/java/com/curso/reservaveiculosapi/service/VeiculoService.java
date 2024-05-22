package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;

public interface VeiculoService {
    void cadastrarVeiculo(VeiculoEntity veiculoEntity);
    VeiculoEntity atualizarVeiculo(VeiculoEntity veiculoEntity, long veiNrId);
}
