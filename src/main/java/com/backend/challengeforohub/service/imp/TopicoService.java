package com.backend.challengeforohub.service.imp;

import com.backend.challengeforohub.dto.input.TopicoDtoInput;
import com.backend.challengeforohub.dto.output.TopicoDtoOut;
import com.backend.challengeforohub.entity.Topico;
import com.backend.challengeforohub.exception.DuplicateDniException;
import com.backend.challengeforohub.exception.ResourceNotFoundException;
import com.backend.challengeforohub.repository.TopicoRepositoriy;
import com.backend.challengeforohub.service.ITopicoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService implements ITopicoService {

    private final static Logger LOGER = LoggerFactory.getLogger(UsuarioService.class);
    private ModelMapper modelMapper;
    private TopicoRepositoriy topicoRepositoriy;

    public TopicoService(ModelMapper modelMapper, TopicoRepositoriy topicoRepositoriy) {
        this.modelMapper = modelMapper;
        this.topicoRepositoriy = topicoRepositoriy;
    }

    @Override
    public List<TopicoDtoOut> listaTopicos() {
        List<TopicoDtoOut> topicoDtoOuts = topicoRepositoriy.findAll()
                .stream().map((t) -> modelMapper.map(t, TopicoDtoOut.class))
                .collect(Collectors.toList());
        return topicoDtoOuts;
    }

    @Override
    public TopicoDtoOut registarTopico(TopicoDtoInput topicoDtoInput) throws DuplicateDniException {
        LOGER.info("topicoDtoInputt --> {}", topicoDtoInput);

        Topico topicoBuscado = (topicoRepositoriy.findByTitulo(topicoDtoInput.getTitulo()));
        if (topicoBuscado != null) {
            throw new DuplicateDniException("El Topico que ingreso ya existe en el sistema");
        }

        Topico topicoARegistrar = modelMapper.map(topicoDtoInput, Topico.class);
        LOGER.info("topicoARegistrar --> {}", topicoARegistrar);


        Topico topicoRegistrado = topicoRepositoriy.save(topicoARegistrar);

        LOGER.info("topicoRegistrado --> {}", topicoRegistrado);

        TopicoDtoOut topicoDtoOut = modelMapper.map(topicoRegistrado, TopicoDtoOut.class);
        LOGER.info("topicoDtoOut --> {}", topicoDtoOut);

        return topicoDtoOut;
    }

    @Override
    public TopicoDtoOut buscaTopico(Long id) throws ResourceNotFoundException {
        LOGER.info("Id input --> {}", id);

        Topico topicoBuscado = topicoRepositoriy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El topico con id " + id + " no existe"));


        LOGER.info("Topico buscado --> {}", topicoBuscado);

        TopicoDtoOut topicoDtoOut = modelMapper.map(topicoBuscado, TopicoDtoOut.class);

        return topicoDtoOut;
    }

    @Override
    public TopicoDtoOut actualizaTopico(Long id, TopicoDtoInput topicoDtoInput) throws ResourceNotFoundException {
        LOGER.info("Id input --> {}", id);
        LOGER.info("topicoDtoInput --> {}", topicoDtoInput);

        TopicoDtoOut topicoBuscado = buscaTopico(id);
        TopicoDtoOut topicoDtoOut = null;
        if (topicoBuscado != null) {
            Topico topicoAActualizar = modelMapper.map(topicoDtoInput, Topico.class);
            topicoAActualizar.setId(topicoBuscado.getId());
            LOGER.info("topicoDtoInput input --> {}", topicoDtoInput);

            Topico topicoActualizado = topicoRepositoriy.save(topicoAActualizar);
            LOGER.info("topicoActualizado --> {}", topicoActualizado);

            topicoDtoOut = modelMapper.map(topicoActualizado, TopicoDtoOut.class);
            LOGER.info("topicoActualizado --> {}", topicoActualizado);

        }

        return topicoDtoOut;
    }

    @Override
    public void eliminarTopico(Long id) throws ResourceNotFoundException {
        if (buscaTopico(id) != null) {
            topicoRepositoriy.deleteById(id);
        }

    }
}
