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
@Table(name = "vei_veiculo", schema = "public")
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vei_nr_id")
    long veiNrId;
    @Column(name = "vei_tx_nome")
    String veiTxNome;
    @Column(name = "vei_tx_marca")
    String veiTxMarca;
    @Column(name = "vei_tx_tipo")
    String veiTxTipo;
}
