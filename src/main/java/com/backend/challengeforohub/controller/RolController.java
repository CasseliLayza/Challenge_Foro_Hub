package com.backend.challengeforohub.controller;


import com.backend.challengeforohub.entity.Rol;
import com.backend.challengeforohub.exception.DuplicateRolException;
import com.backend.challengeforohub.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    IRolesService rolesService;

    @GetMapping("/list")
    public List<Rol> listarRoles() {
        return rolesService.listaRoles();
    }

    @PostMapping("/register")
    public Rol registraRole(@RequestBody Rol rol) throws DuplicateRolException {
        return rolesService.setRoles(rol);
    }


    @DeleteMapping("/delete/{id}")
    public void eliminarRol(@PathVariable Long id) throws DuplicateRolException {
        rolesService.eliminarRol(id);
    }
}
