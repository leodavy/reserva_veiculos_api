package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Admin", description = "Serviço para painel de administração")
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final UsuarioService usuarioService;

    @GetMapping("/listarUsuarios")
    @Operation(summary = "Listagem de todos os usuários. Requer acesso de administrador", description = "Permite aos administradores listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
