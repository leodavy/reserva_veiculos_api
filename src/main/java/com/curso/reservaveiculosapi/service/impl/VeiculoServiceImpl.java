package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.repository.VeiculoRepository;
import com.curso.reservaveiculosapi.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoRepository veiculoRepository;

    @Override
    public void cadastrarVeiculo(VeiculoEntity veiculoEntity) {
        veiculoRepository.save(veiculoEntity);
    }

    public VeiculoEntity atualizarVeiculo(VeiculoEntity veiculoAtualizado, long veiNrId) {
        VeiculoEntity veiculoExistente = veiculoRepository.findById(veiNrId)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado, ID: " + veiNrId));

        veiculoExistente.setVeiTxNome(veiculoAtualizado.getVeiTxNome());
        veiculoExistente.setVeiTxMarca(veiculoAtualizado.getVeiTxMarca());
        veiculoExistente.setVeiTxTipo(veiculoAtualizado.getVeiTxTipo());
        return veiculoRepository.save(veiculoExistente);
    }
}

