package com.backend.challengeforohub.service;

import com.backend.challengeforohub.dto.input.UsuarioDtoInput;
import com.backend.challengeforohub.dto.output.UsuarioDtoOut;
import com.backend.challengeforohub.exception.ResourceNotFoundException;
import com.backend.challengeforohub.exception.DuplicateDniException;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDtoOut> listaUsuarios();

    UsuarioDtoOut registarUsuarios(UsuarioDtoInput usuario) throws DuplicateDniException;

    UsuarioDtoOut buscarUsuario(Long id) throws ResourceNotFoundException;

    UsuarioDtoOut actualizarUsuario(Long id, UsuarioDtoInput usuario) throws ResourceNotFoundException;

    void eliminarUsuario(Long id) throws ResourceNotFoundException;
    void eliminarUsuarios() throws ResourceNotFoundException;
    void resetearIds() throws ResourceNotFoundException;

}
