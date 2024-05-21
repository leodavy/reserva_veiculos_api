package com.curso.reservaveiculosapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UsuarioPerfilKey implements Serializable {

    @Column(name = "usu_nr_id")
    private long usuNrId;
    @Column(name = "per_nr_id")
    private long perNrId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPerfilKey that = (UsuarioPerfilKey) o;
        return usuNrId == that.usuNrId && perNrId == that.perNrId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(usuNrId, perNrId);
    }
}
