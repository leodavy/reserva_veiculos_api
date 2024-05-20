package com.curso.reservaveiculosapi.model.forms;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
    private long usuNrId;
    @NotBlank(message = "O Nome é obrigatório!")
    private String usuTxNome;
    @NotBlank(message = "O Login é obrigatório!")
    private String usuTxLogin;
    @NotBlank(message = "A senha é obrigatória!")
    private String usuTxSenha;
}


