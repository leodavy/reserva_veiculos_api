package com.curso.reservaveiculosapi.model.entity;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "usu_nr_id"))
    @Column(name = "role")
    private List<String> roles;
    public UsuarioDTO toDTO() {
        return UsuarioDTO.builder()
                .usuNrId(usuNrId)
                .usuTxNome(usuTxNome)
                .usuTxLogin(usuTxLogin)
                .roles(roles)
                .build();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
