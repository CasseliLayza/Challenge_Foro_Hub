package com.backend.challengeforohub.service;

import com.backend.challengeforohub.dto.input.TopicoDtoInput;
import com.backend.challengeforohub.dto.input.UsuarioDtoInput;
import com.backend.challengeforohub.dto.output.TopicoDtoOut;
import com.backend.challengeforohub.dto.output.UsuarioDtoOut;
import com.backend.challengeforohub.entity.Topico;
import com.backend.challengeforohub.exception.DuplicateDniException;
import com.backend.challengeforohub.exception.ResourceNotFoundException;

import java.util.List;

public interface ITopicoService {
    List<TopicoDtoOut> listaTopicos();

    TopicoDtoOut registarTopico(TopicoDtoInput topicoDtoInput) throws DuplicateDniException;

    TopicoDtoOut buscaTopico(Long id) throws ResourceNotFoundException;

    TopicoDtoOut actualizaTopico(Long id, TopicoDtoInput topicoDtoInput) throws ResourceNotFoundException;

    void eliminarTopico(Long id) throws ResourceNotFoundException;
}
