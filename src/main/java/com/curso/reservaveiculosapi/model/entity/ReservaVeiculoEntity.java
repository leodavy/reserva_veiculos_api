package com.curso.reservaveiculosapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vus_veiculo_usuario", schema = "public")
public class ReservaVeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vus_nr_id")
    private long vusNrId;
    @Column(name = "vus_dt_date")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Maceio")
    private Date vusDtDate;
    @Column(name = "vei_nr_id")
    private long veiNrId;
    @Column(name = "usu_nr_id")
    private long usuNrId;

}
