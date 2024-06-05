package com.curso.reservaveiculosapi.service.impl;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.ImagemVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.repository.ImagemVeiculoRepository;
import com.curso.reservaveiculosapi.repository.ReservaVeiculoRepository;
import com.curso.reservaveiculosapi.repository.UsuarioRepository;
import com.curso.reservaveiculosapi.repository.VeiculoRepository;
import com.curso.reservaveiculosapi.service.VeiculoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.FileNameMap;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ReservaVeiculoRepository reservaVeiculoRepository;
    private final ImagemVeiculoRepository imagemVeiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public void cadastrarVeiculo(VeiculoEntity veiculoEntity, Long usuNrId) {
        Optional<UsuarioEntity> usuarioDTO = usuarioRepository.findById(usuNrId);
        veiculoEntity.setUsuNrId(usuNrId);
        veiculoRepository.save(veiculoEntity);
    }
    @Transactional
    public VeiculoEntity atualizarVeiculo(VeiculoEntity veiculoAtualizado, long veiNrId) {
        VeiculoEntity veiculoExistente = veiculoRepository.findById(veiNrId).orElseThrow(() -> new RuntimeException("Veículo não encontrado, ID: " + veiNrId));

        veiculoExistente.setVeiTxNome(veiculoAtualizado.getVeiTxNome());
        veiculoExistente.setVeiTxMarca(veiculoAtualizado.getVeiTxMarca());
        veiculoExistente.setVeiTxTipo(veiculoAtualizado.getVeiTxTipo());
        return veiculoRepository.save(veiculoExistente);
    }


    public ReservaVeiculoEntity reservarVeiculo(long veiNrId, long usuNrId, Date vusDtDate) {
        ReservaVeiculoEntity reservaVeiculo = ReservaVeiculoEntity.builder().veiNrId(veiNrId).usuNrId(usuNrId).vusDtDate(vusDtDate).build();
        return reservaVeiculoRepository.save(reservaVeiculo);
    }


    public List<VeiculoEntity> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public void adicionarImagemVeiculo(Long veiNrId, MultipartFile imagemVeiculo) throws IOException {
        VeiculoEntity veiculoEntity = veiculoRepository.findById(veiNrId).orElseThrow(() -> new RuntimeException("Veículo não encontrado: " + veiNrId));
        List<ImagemVeiculoEntity> existingImages = imagemVeiculoRepository.findByVeiNrId(veiNrId);
        if (imagemVeiculo.isEmpty()) {
            throw new RuntimeException("Nenhuma imagem selecionada");
        }
        if (existingImages.size() >= 6) {
            throw new RuntimeException("O veículo pode ter no máximo 6 imagens");
        }
        ImagemVeiculoEntity imagemVeiculoEntity = ImagemVeiculoEntity.builder().imvTxNome(imagemVeiculo.getOriginalFilename()).imvBtBytes(imagemVeiculo.getBytes()).imvTxExtensao(imagemVeiculo.getContentType()).veiNrId(veiNrId).build();
        imagemVeiculoRepository.save(imagemVeiculoEntity);
    }

    public void atualizarImagemVeiculo(Long veiNrId, Long imvNrId, MultipartFile novaImagem) throws RuntimeException {
        VeiculoEntity veiculoEntity = veiculoRepository.findById(veiNrId).orElseThrow(() -> new RuntimeException("Veículo não encontrado: " + veiNrId));
        ImagemVeiculoEntity imagemVeiculoEntity = imagemVeiculoRepository.findById(imvNrId).orElseThrow(() -> new RuntimeException("Imagem não encontrada: " + imvNrId));
        try {
            byte[] bytes = novaImagem.getBytes();
            imagemVeiculoEntity.setImvBtBytes(bytes);
            imagemVeiculoEntity.setImvTxNome(novaImagem.getOriginalFilename());
            imagemVeiculoEntity.setImvTxExtensao(FilenameUtils.getExtension(novaImagem.getOriginalFilename()));
            imagemVeiculoRepository.save(imagemVeiculoEntity);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao ler arquivo de imagem", e);
        }
    }

    @Transactional
    public void excluirImagemVeiculo(Long veiNrId, Long imvNrId) throws RuntimeException {
        ImagemVeiculoEntity imagemVeiculoEntity = imagemVeiculoRepository.findById(imvNrId).orElseThrow(() -> new RuntimeException("Imagem não encontrada: " + imvNrId));
        imagemVeiculoRepository.delete(imagemVeiculoEntity);
    }

    @Transactional
    public void excluirVeiculo(Long veiNrId) {
        imagemVeiculoRepository.deleteByVeiNrId(veiNrId);
        reservaVeiculoRepository.deleteByVeiNrId(veiNrId);
        veiculoRepository.deleteById(veiNrId);
    }


    public List<ImagemVeiculoEntity> getImagensByVeiculoId(Long veiNrId) {
        return imagemVeiculoRepository.findByVeiNrId(veiNrId);
    }

    public ImagemVeiculoEntity getImagemById(Long imvNrId) {
        return imagemVeiculoRepository.findById(imvNrId).orElseThrow(() -> new RuntimeException("Imagem não encontrada: " + imvNrId));
    }
    public Optional<VeiculoEntity> findById(Long usuNrId) {
        return veiculoRepository.findById(usuNrId);
    }

    @Override
    public List<ReservaVeiculoEntity> getReservasByUsuario(Long usuNrId) {
        return reservaVeiculoRepository.findByUsuNrId(usuNrId);
    }
    public List<ReservaVeiculoEntity> getAllReservas() {
        return reservaVeiculoRepository.findAll();
    }
}



