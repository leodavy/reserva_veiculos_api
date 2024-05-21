package com.curso.reservaveiculosapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usp_usuario_perfil", schema = "public")
public class UsuarioPerfilEntity implements Serializable {
    @EmbeddedId
    private UsuarioPerfilKey uspUsuarioPerfilKey;
}
