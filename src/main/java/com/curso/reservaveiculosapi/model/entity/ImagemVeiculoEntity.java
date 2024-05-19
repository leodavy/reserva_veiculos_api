package com.curso.reservaveiculosapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Blob;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imv_imagen_veiculo", schema = "public")
public class ImagemVeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imv_nr_id")
    private long imvNrId;
    @Column(name = "imv_tx_nome")
    private String imvTxNome;
    @Lob
    @Column(name = "imv_bt_bytes")
    private byte[] imbBtBytes;
    @Column(name = "imv_tx_extensao")
    private String imvTxExtensao;
    @Column(name = "vei_nr_id")
    private long veiNrId;
}
