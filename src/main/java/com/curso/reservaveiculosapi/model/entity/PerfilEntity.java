package com.curso.reservaveiculosapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "per_perfil", schema = "public")
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_nr_id")
    private long perNrId;
    @Column(name = "per_tx_nome")
    private String perTxNome;
    @ManyToMany(mappedBy = "perfis")
    private List<UsuarioEntity> usuarios;
}

