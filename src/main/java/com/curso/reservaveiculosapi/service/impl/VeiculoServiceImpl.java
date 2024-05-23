package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.model.entity.ImagemVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.repository.ImagemVeiculoRepository;
import com.curso.reservaveiculosapi.repository.ReservaVeiculoRepository;
import com.curso.reservaveiculosapi.repository.VeiculoRepository;
import com.curso.reservaveiculosapi.service.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ReservaVeiculoRepository reservaVeiculoRepository;
    private final ImagemVeiculoRepository imagemVeiculoRepository;

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


    public ReservaVeiculoEntity reservarVeiculo(long veiNrId, long usuNrId, Date vusDtDate) {
        ReservaVeiculoEntity reservaVeiculo = ReservaVeiculoEntity.builder()
                .veiNrId(veiNrId)
                .usuNrId(usuNrId)
                .vusDtDate(vusDtDate)
                .build();
        return reservaVeiculoRepository.save(reservaVeiculo);
    }

    public void adicionarImagemVeiculo(Long veiNrId, MultipartFile imagemVeiculo) throws IOException {
        VeiculoEntity veiculoEntity = veiculoRepository.findById(veiNrId).orElseThrow(() -> new RuntimeException("Veículo não encontrado: " + veiNrId));
        List<ImagemVeiculoEntity> existingImages = imagemVeiculoRepository.findByVeiNrId(veiNrId);
        if(imagemVeiculo.isEmpty()) {
            throw new RuntimeException("Nenhuma imagem selecionada");
        }
        if (existingImages.size() >= 6) {
            throw new RuntimeException("O veículo pode ter no máximo 6 imagens");
        }
        ImagemVeiculoEntity imagemVeiculoEntity = ImagemVeiculoEntity.builder()
                .imvTxNome(imagemVeiculo.getOriginalFilename())
                .imvBtBytes(imagemVeiculo.getBytes())
                .imvTxExtensao(imagemVeiculo.getContentType())
                .veiNrId(veiNrId)
                .build();
        imagemVeiculoRepository.save(imagemVeiculoEntity);
    }
}



