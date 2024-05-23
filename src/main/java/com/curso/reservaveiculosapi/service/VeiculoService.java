package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public interface VeiculoService {
    void cadastrarVeiculo(VeiculoEntity veiculoEntity);
    VeiculoEntity atualizarVeiculo(VeiculoEntity veiculoEntity, long veiNrId);
    ReservaVeiculoEntity reservarVeiculo(long veiNrId, long usuNrId, Date vusDtDate);

    void adicionarImagemVeiculo(Long veiNrId, MultipartFile imagemVeiculo) throws IOException;


    void excluirVeiculo(Long veiNrId);
}
