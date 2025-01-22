package com.backend.challengeforohub.controller;

import com.backend.challengeforohub.dto.input.TopicoDtoInput;
import com.backend.challengeforohub.dto.output.TopicoDtoOut;
import com.backend.challengeforohub.exception.DuplicateDniException;
import com.backend.challengeforohub.exception.ResourceNotFoundException;
import com.backend.challengeforohub.service.ITopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topics")
@CrossOrigin(originPatterns = "*")
public class TopicoController {

    @Autowired
    ITopicoService topicoService;

    @GetMapping("/list")
    public ResponseEntity<List<TopicoDtoOut>> listarTopicos() {
        return new ResponseEntity<>(topicoService.listaTopicos(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<TopicoDtoOut> registerUsuario(@RequestBody @Valid TopicoDtoInput topicoDtoInput) throws DuplicateDniException {
        return new ResponseEntity<>(topicoService.registarTopico(topicoDtoInput), HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<TopicoDtoOut> buscartopico(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(topicoService.buscaTopico(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicoDtoOut> actualizarTopico(@PathVariable Long id, @RequestBody TopicoDtoInput topicoDtoInput) throws ResourceNotFoundException {
        return new ResponseEntity<>(topicoService.actualizaTopico(id, topicoDtoInput), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarTopico(@PathVariable Long id) throws ResourceNotFoundException {
        topicoService.eliminarTopico(id);
    }


}
