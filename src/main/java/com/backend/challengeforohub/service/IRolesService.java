package com.backend.challengeforohub.service;

import com.backend.challengeforohub.entity.Rol;
import com.backend.challengeforohub.exception.DuplicateRolException;

import java.util.List;

public interface IRolesService {

    List<Rol> listaRoles();

    Rol setRoles(Rol rol) throws DuplicateRolException;

    void eliminarRol(Long id) throws DuplicateRolException;

}
