package com.curso.reservaveiculosapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private long usuNrId;
    private String usuTxNome;
    private String usuTxLogin;
}
