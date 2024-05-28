package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface VeiculoService {
    void cadastrarVeiculo(VeiculoEntity veiculoEntity);
    VeiculoEntity atualizarVeiculo(VeiculoEntity veiculoEntity, long veiNrId);
    ReservaVeiculoEntity reservarVeiculo(long veiNrId, long usuNrId, Date vusDtDate);
    List<VeiculoEntity> listarVeiculos();
    void adicionarImagemVeiculo(Long veiNrId, MultipartFile imagemVeiculo) throws IOException;
    void atualizarImagemVeiculo(Long veiNrId, Long imvNrId, MultipartFile imagemVeiculo) throws RuntimeException;
    void excluirImagemVeiculo(Long veiNrId, Long imvNrId) throws RuntimeException;
    void excluirVeiculo(Long veiNrId);
}
