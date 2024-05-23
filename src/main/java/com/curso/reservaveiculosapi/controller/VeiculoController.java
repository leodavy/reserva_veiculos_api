package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.entity.ReservaVeiculoEntity;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@Tag(name = "Veículos", description = "Serviços para gerenciar os veículos")
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping("/cadastrarVeiculo")
    @Operation(summary = "Cadastrar veículo", description = "Permite aos usuários cadastrar um veículo no sistema")
    public ResponseEntity<String> cadastrarVeiculo(@RequestBody VeiculoEntity veiculo) {
        veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.ok("Veículo cadastrado com sucesso!");
    }

    @PutMapping("/{veiNrId}/atualizarVeiculo")
    @Operation(summary = "Atualizar veículo", description = "Permite aos usuários atualizar um veículo já cadastrado no sistema")
    public ResponseEntity<VeiculoEntity> atualizarVeiculo(
            @PathVariable Long veiNrId,
            @RequestBody VeiculoEntity veiculoAtualizado
    ) {
        VeiculoEntity veiculo = veiculoService.atualizarVeiculo(veiculoAtualizado, veiNrId);
        return ResponseEntity.ok(veiculo);
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

    @PutMapping("/atualizarImagemVeiculo/{veiculoId}/{imvNrId}")
    @Operation(summary = "Atualizar imagem do Veículo", description = "Substitui uma imagem específica de um veículo")
    public ResponseEntity<String> atualizarImagemVeiculo(@PathVariable Long veiculoId,
                                                         @PathVariable Long imvNrId,
                                                         @RequestParam("imagem") MultipartFile imagem) {
        try {
            veiculoService.atualizarImagemVeiculo(veiculoId, imvNrId, imagem);
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

}
