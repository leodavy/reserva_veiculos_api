package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.PerfilEntity;
import com.curso.reservaveiculosapi.service.PerfilService;
import com.curso.reservaveiculosapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Admin", description = "Serviço para painel de administração")
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    @GetMapping("/listarUsuarios")
    @Operation(summary = "Listagem de todos os usuários. Requer acesso de administrador", description = "Permite aos administradores listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/criarPerfil")
    @Operation(summary = "Criar perfil. Requer acesso de administrador", description = "Permite aos administradores criar um perfil")
    @ApiResponse(responseCode = "200", description = "Perfil criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PerfilEntity.class)))
    public ResponseEntity<PerfilEntity> createProfile(@RequestBody String perTxNome) {
        PerfilEntity perfil = perfilService.criarPerfil(perTxNome);
        return ResponseEntity.ok(perfil);
    }
    @PostMapping("/associarPerfilUsuario")
    @ApiResponse(responseCode = "200", description = "Associação de usuário ao perfil realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PerfilEntity.class)))
    @Operation(summary = "Associar usuário a um perfil. Requer acesso de administrador", description = "Permite aos administradores criar um perfil")
    public ResponseEntity<String> associatePerfil(@RequestParam long usuNrId, @RequestParam long perNrId) {
        usuarioService.associarPerfilUsuario(usuNrId, perNrId);
        return ResponseEntity.ok("Usuário associado ao perfil com sucesso!");
    }



}