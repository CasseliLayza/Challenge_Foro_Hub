package com.backend.challengeforohub.controller;

import com.backend.challengeforohub.dto.input.UsuarioDtoInput;
import com.backend.challengeforohub.dto.output.UsuarioDtoOut;
import com.backend.challengeforohub.exception.DuplicateDniException;
import com.backend.challengeforohub.exception.ResourceNotFoundException;
import com.backend.challengeforohub.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDtoOut>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listaUsuarios(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDtoOut> createUsuario(@RequestBody @Valid UsuarioDtoInput usuarioDtoInput) throws DuplicateDniException {
        return new ResponseEntity<>(usuarioService.registarUsuarios(usuarioDtoInput),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDtoOut> registerUsuario(@RequestBody @Valid UsuarioDtoInput usuarioDtoInput) throws DuplicateDniException {
        usuarioDtoInput.setEsAdmin(false);
        return createUsuario(usuarioDtoInput);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UsuarioDtoOut> buscarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.buscarUsuario(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDtoOut> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDtoInput usuario) throws ResourceNotFoundException {
        return new ResponseEntity<>( usuarioService.actualizarUsuario(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarUaurio(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioService.eliminarUsuario(id);
    }


    @DeleteMapping("/deleteall")
    public void eliminarUsuarios() throws ResourceNotFoundException {
        usuarioService.eliminarUsuarios();
    }

    @PostMapping("/reesetids")
    public void resertIds() throws ResourceNotFoundException {

        usuarioService.resetearIds();
    }
}
