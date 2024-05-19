package com.curso.reservaveiculosapi.service;

import com.curso.reservaveiculosapi.model.dto.UsuarioDTO;
import com.curso.reservaveiculosapi.model.forms.AutenticacaoForm;
import com.curso.reservaveiculosapi.model.forms.UsuarioForm;

public interface UsuarioService {
    String autenticar(AutenticacaoForm autenticacaoForm);
    UsuarioDTO insert(UsuarioForm usuarioForm);


}
