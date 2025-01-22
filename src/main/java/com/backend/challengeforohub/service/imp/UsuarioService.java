package com.backend.challengeforohub.service.imp;

import com.backend.challengeforohub.dto.input.UsuarioDtoInput;
import com.backend.challengeforohub.dto.output.UsuarioDtoOut;
import com.backend.challengeforohub.entity.Rol;
import com.backend.challengeforohub.entity.Usuario;
import com.backend.challengeforohub.exception.ResourceNotFoundException;
import com.backend.challengeforohub.repository.RolRepository;
import com.backend.challengeforohub.repository.UsuarioRepositoy;
import com.backend.challengeforohub.service.IUsuarioService;
import com.backend.challengeforohub.exception.DuplicateDniException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final static Logger LOGER = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private UsuarioRepositoy usuarioRepositoy;
    @Autowired
    private EntityManager entityManager;

    private ModelMapper modelMapper;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepositoy usuarioRepositoy, EntityManager entityManager, ModelMapper modelMapper, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepositoy = usuarioRepositoy;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsuarioDtoOut> listaUsuarios() {
        List<UsuarioDtoOut> usuariosDto = usuarioRepositoy.findAll()
                .stream().map((usuario) -> modelMapper.map(usuario, UsuarioDtoOut.class))
                .collect(Collectors.toList());
        return usuariosDto;
    }

    @Override
    public UsuarioDtoOut registarUsuarios(UsuarioDtoInput usuarioDtoInput) throws DuplicateDniException {

        LOGER.info("Usuario input --> {}", usuarioDtoInput);

        Usuario usuarioBuscado = (usuarioRepositoy.findByDni(usuarioDtoInput.getDni()));
        if (usuarioBuscado != null) {
            throw new DuplicateDniException("El DNI que ingreso ya existe en el sistema");
        }

        Usuario usuarioARegistrar = modelMapper.map(usuarioDtoInput, Usuario.class);
        LOGER.info("usuarioARegistrar --> {}", usuarioARegistrar);

        //////////////////////////ROLES
        List<Rol> roles = new ArrayList<>();
        Optional<Rol> rolOptionalUser = rolRepository.findByNombre("ROLE_USER");
        rolOptionalUser.ifPresent(role -> roles.add(role));

        if (usuarioDtoInput.isEsAdmin()) {
            Optional<Rol> rolOptionalAdmin = rolRepository.findByNombre("ROLE_ADMIN");
            rolOptionalAdmin.ifPresent(role -> roles.add(role));
        }

        usuarioARegistrar.setRoles(roles);
        usuarioARegistrar.setPassword(passwordEncoder.encode(usuarioDtoInput.getPassword()));

        Usuario usuarioRegistrado = usuarioRepositoy.save(usuarioARegistrar);

        LOGER.info("Usuario registrado --> {}", usuarioRegistrado);

        UsuarioDtoOut usuarioDtoOut = modelMapper.map(usuarioRegistrado, UsuarioDtoOut.class);
        LOGER.info("usuarioDtoOut --> {}", usuarioDtoOut);

        return usuarioDtoOut;
    }

    @Override
    public UsuarioDtoOut buscarUsuario(Long id) throws ResourceNotFoundException {
        LOGER.info("Id input --> {}", id);

        Usuario usuarioBuscado = usuarioRepositoy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario con id " + id + " no existe"));


        LOGER.info("Usuario buscado --> {}", usuarioBuscado);

        UsuarioDtoOut usuarioDtoOut = modelMapper.map(usuarioBuscado, UsuarioDtoOut.class);

        return usuarioDtoOut;
    }


    @Override
    public UsuarioDtoOut actualizarUsuario(Long id, UsuarioDtoInput usuario) throws ResourceNotFoundException {
        LOGER.info("Id input --> {}", id);
        LOGER.info("Usuario input --> {}", usuario);

        UsuarioDtoOut usuarioBuscado = buscarUsuario(id);
        UsuarioDtoOut usuarioDtoOut = null;
        if (usuarioBuscado != null) {
            Usuario usuarioAActualizar = modelMapper.map(usuario, Usuario.class);
            usuarioAActualizar.setId(usuarioBuscado.getId());
            LOGER.info("usuarioAActualizar input --> {}", usuario);

            Usuario usuarioActualizado = usuarioRepositoy.save(usuarioAActualizar);
            LOGER.info("usuarioActualizado --> {}", usuarioActualizado);

            usuarioDtoOut = modelMapper.map(usuarioActualizado, UsuarioDtoOut.class);
            LOGER.info("usuarioActualizado --> {}", usuarioActualizado);

        }

        return usuarioDtoOut;
    }

    @Override
    public void eliminarUsuario(Long id) throws ResourceNotFoundException {

        if (buscarUsuario(id) != null) {
            usuarioRepositoy.deleteById(id);
        }

    }

    @Override
    public void eliminarUsuarios() throws ResourceNotFoundException {

        if (listaUsuarios().size() != 0) {
            usuarioRepositoy.deleteAll();
        } else {
            throw new ResourceNotFoundException("No existe usuarios ");
        }
    }

    @Override
    @Transactional
    public void resetearIds() throws ResourceNotFoundException {
        if (listaUsuarios().size() != 0) {
            throw new ResourceNotFoundException("Elimina los usuarios para resetear ids");
        } else {
            entityManager.createNativeQuery("ALTER TABLE USUARIOS AUTO_INCREMENT=1").executeUpdate();

        }

    }


}

