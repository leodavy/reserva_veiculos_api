package com.curso.reservaveiculosapi.controller;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.VeiculoEntity;
import com.curso.reservaveiculosapi.model.forms.AutenticacaoForm;
import com.curso.reservaveiculosapi.model.forms.UsuarioForm;
import com.curso.reservaveiculosapi.service.UsuarioService;
import com.curso.reservaveiculosapi.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Usuário", description = "Serviços para gerenciar os usuários")
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final VeiculoService veiculoService;

    @PostMapping("/autenticar")
    @Operation(summary = "Autenticação usuário")
    @ApiResponse(responseCode = "200", description = "Ok, usuário autenticado", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    public String autenticar(@Valid @RequestBody AutenticacaoForm form) {
        System.out.println("usuário autenticado!");
        return this.usuarioService.autenticar(form);
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastro de usuário", description = """
            Realiza o cadastro de um usuário.
            """)
    @ApiResponse(responseCode = "200", description = "Usuário que foi cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    public UsuarioDTO insert(@Valid @RequestBody UsuarioForm usuarioForm) {
        return usuarioService.insert(usuarioForm);
    }

}
