package com.curso.reservaveiculosapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usp_usuario_perfil", schema = "public")
public class UsuarioPerfilEntity {
    @Id
    @Column(name = "usu_nr_id")
    long usuNrId;
    @Column(name = "per_nr_id")
    long perNrId;
}
