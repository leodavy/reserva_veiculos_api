package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.ImagemVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Veículos", description = "Serviços para gerenciar os veículos")
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping("/cadastrarVeiculo")
    @Operation(summary = "Cadastrar veículo", description = "Permite aos usuários cadastrar um veículo no sistema")
    public ResponseEntity<String> cadastrarVeiculo(@RequestBody VeiculoEntity veiculo) {
        veiculoService.cadastrarVeiculo(veiculo, veiculo.getUsuNrId());
        return ResponseEntity.ok("Veículo cadastrado com sucesso!");
    }

    @PutMapping("/atualizarVeiculo/{veiNrId}/")
    @Operation(summary = "Atualizar veículo", description = "Permite aos usuários atualizar um veículo já cadastrado no sistema")
    public ResponseEntity<VeiculoEntity> atualizarVeiculo(
            @PathVariable Long veiNrId,
            @RequestBody VeiculoEntity veiculoAtualizado
    ) {
        VeiculoEntity veiculo = veiculoService.atualizarVeiculo(veiculoAtualizado, veiNrId);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/listarVeiculos")
    @Operation(summary = "Listagem de todos os usuários. Requer acesso de administrador", description = "Permite aos administradores listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    public List<VeiculoEntity> listarUsuarios() {
        return veiculoService.listarVeiculos();
    }

    @PostMapping("/reservarVeiculo")
    @Operation(summary = "Reservar veículo", description = "Permite aos usuários reservar um veículo já cadastrado no sistema")
    public ResponseEntity<ReservaVeiculoEntity> reservarVeiculo(@RequestBody ReservaVeiculoEntity reservaVeiculoEntity) {
        ReservaVeiculoEntity reservaVeiculo = veiculoService.reservarVeiculo(
                reservaVeiculoEntity.getVeiNrId(),
                reservaVeiculoEntity.getUsuNrId(),
                reservaVeiculoEntity.getVusDtDate());
        return ResponseEntity.ok(reservaVeiculo);
    }

    @PostMapping("/{veiNrId}/adicionarImagem")
    @Operation(summary = "Adicionar imagens ao veículo", description = "Permite aos usuários adicionar uma imagem ao veículo")
    public ResponseEntity<String> addImageToVeiculo(@PathVariable Long veiNrId, @RequestParam("imagem") MultipartFile imagem) {
        try {
            veiculoService.adicionarImagemVeiculo(veiNrId, imagem);
            return ResponseEntity.ok("Imagem adicionada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/excluirVeiculo/{veiNrId}")
    @Operation(summary = "Excluir Veículo", description = "Exclui um veículo do sistema, juntamente com suas imagens e reservas")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long veiNrId) {
        veiculoService.excluirVeiculo(veiNrId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarImagemVeiculo/{veiNrId}/{imvNrId}")
    @Operation(summary = "Atualizar imagem do Veículo", description = "Substitui uma imagem específica de um veículo")
    public ResponseEntity<String> atualizarImagemVeiculo(@PathVariable Long veiNrId,
                                                         @PathVariable Long imvNrId,
                                                         @RequestParam("imagem") MultipartFile imagem) {
        try {
            veiculoService.atualizarImagemVeiculo(veiNrId, imvNrId, imagem);
            return ResponseEntity.ok("Imagem do veículo atualizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar imagem do veículo: " + e.getMessage());
        }
    }
    @DeleteMapping("/excluirImagemVeiculo/{veiNrId}/{imvNrId}")
    @Operation(summary = "Excluir imagem do Veículo", description = "Exclui uma imagem específica de um veículo")
    public ResponseEntity<String> excluirImagemVeiculo(@PathVariable Long veiNrId,@PathVariable Long imvNrId) {
        try {
            veiculoService.excluirImagemVeiculo(veiNrId, imvNrId);
            return ResponseEntity.ok("Imagem do veículo excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir imagem do veículo: " + e.getMessage());
        }
    }

    @GetMapping("/{veiNrId}/imagens")
    @Operation(summary = "Listar todas as imagens de um veículo", description = "Permite aos usuários listar todas as imagens de um veículo")
    public ResponseEntity<List<ImagemVeiculoEntity>> listarImagensPorVeiculo(@PathVariable Long veiNrId) {
        List<ImagemVeiculoEntity> imagens = veiculoService.getImagensByVeiculoId(veiNrId);
        return ResponseEntity.ok(imagens);
    }
    @GetMapping("/imagens/{imvNrId}")
    @Operation(summary = "Buscar uma imagem específica pelo seu id ID", description = "Permite aos usuários buscar uma imagem específica a partir do seu ID")
    public ResponseEntity<ImagemVeiculoEntity> buscarImagemPorId(@PathVariable Long imvNrId) {
        ImagemVeiculoEntity imagem = veiculoService.getImagemById(imvNrId);
        return ResponseEntity.ok(imagem);
    }
    @GetMapping("/{veiNrId}")
    @Operation(summary = "Buscar um veículo pelo seu ID", description = "Permite aos usuários buscar um veículo pelo seu ID")
    public ResponseEntity<VeiculoEntity> findById(@PathVariable Long veiNrId) {
        Optional<VeiculoEntity> userOptional = veiculoService.findById(veiNrId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/reservas/{usuNrId}")
    @Operation(summary = "Listar todas as reservas feitas por um usuário", description = "Permite aos usuários buscar todas as reservas")
    public ResponseEntity<List<ReservaVeiculoEntity>> findReservasByUsuarioId(@PathVariable Long usuNrId) {
        List<ReservaVeiculoEntity> reservas = veiculoService.getReservasByUsuario(usuNrId);
        return ResponseEntity.ok(reservas);
    }
    @GetMapping("/reservas")
    @Operation(summary = "Listar todas as reservas", description = "Permite aos usuários buscar todas as reservas")
    public ResponseEntity<List<ReservaVeiculoEntity>> findAllReservas() {
        List<ReservaVeiculoEntity> reservas = veiculoService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }
}
