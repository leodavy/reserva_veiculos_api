package com.curso.reservaveiculosapi.model.forms;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoForm(
    @NotBlank(message = "Campo 'Login' é obrigatório")
    String login,
    @NotBlank(message = "Campo 'Senha' é obrigatório")
    String senha
){
}
