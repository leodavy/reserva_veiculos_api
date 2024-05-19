package com.curso.reservaveiculosapi.model.entity;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usu_usuario", schema = "public")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    long usuNrId;
    @Column(name = "usu_tx_nome")
    String usuTxNome;
    @Column(name = "usu_tx_login")
    String usuTxLogin;
    @Column(name = "usu_tx_senha")
    String usuTxSenha;
    public UsuarioDTO toDTO() {
        return UsuarioDTO.builder()
                .usuNrId(usuNrId)
                .usuTxNome(usuTxNome)
                .usuTxLogin(usuTxLogin)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return usuTxSenha;
    }

    @Override
    public String getUsername() {
        return usuTxLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
