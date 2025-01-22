package com.backend.challengeforohub.service.imp;

import com.backend.challengeforohub.entity.Rol;
import com.backend.challengeforohub.repository.RolRepository;
import com.backend.challengeforohub.service.IRolesService;
import com.backend.challengeforohub.exception.DuplicateRolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService implements IRolesService {
    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> listaRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol setRoles(Rol rol) throws DuplicateRolException {
        Optional<Rol> rolOptional = rolRepository.findByNombre(rol.getNombre());

        if (rolOptional.isPresent()){
            new DuplicateRolException("El rol " + rol.getNombre() + " ya existe");

        }

        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Long id) throws DuplicateRolException {
        rolRepository.findById(id)
                .orElseThrow(() -> new DuplicateRolException("Verificar id: " + id + ", de identificacion"));

        rolRepository.deleteById(id);


    }
}
