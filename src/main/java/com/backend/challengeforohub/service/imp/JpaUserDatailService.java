package com.backend.challengeforohub.service.imp;

import com.backend.challengeforohub.entity.Usuario;
import com.backend.challengeforohub.repository.UsuarioRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDatailService implements UserDetailsService {

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;



    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Usuario> usuarioOptional = usuarioRepositoy.findByUserName(username);

        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario %s no existe en la base de datos!", username));
        }

        Usuario usuarioEncontrado = usuarioOptional.orElseThrow();

        List<GrantedAuthority> authorities = usuarioEncontrado.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());

        return new User(
                usuarioEncontrado.getUserName(),
                usuarioEncontrado.getPassword(),
                usuarioEncontrado.isEstaActivo(),
                true,
                true,
                true,
                authorities);

    }
}
