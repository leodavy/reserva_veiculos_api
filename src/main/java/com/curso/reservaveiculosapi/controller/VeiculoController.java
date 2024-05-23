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
    @PostMapping("/{veiNrId}/atualizarVeiculo")
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
    public ResponseEntity<String> addImageToVeiculo(@PathVariable Long veiNrId, @RequestParam("image") MultipartFile imagem) {
        try {
            veiculoService.adicionarImagemVeiculo(veiNrId, imagem);
            return ResponseEntity.ok("Imagem adicionada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}