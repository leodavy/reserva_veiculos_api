package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Veículos", description = "Serviços para gerenciar os veículos")
@RequestMapping("veiculo")
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

}
