package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.entity.UsuarioEntity;
import com.curso.reservaveiculosapi.model.forms.AutenticacaoForm;
import com.curso.reservaveiculosapi.model.forms.UsuarioForm;

import java.util.List;

public interface UsuarioService {
    String autenticar(AutenticacaoForm autenticacaoForm);
    UsuarioDTO insert(UsuarioForm usuarioForm);
    List<UsuarioDTO> listarUsuarios();
    void associarPerfilUsuario(Long usuNrId, Long perNrId);

}
