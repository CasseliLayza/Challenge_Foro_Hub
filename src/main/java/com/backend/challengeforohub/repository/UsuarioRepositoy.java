package com.backend.challengeforohub.repository;

import com.backend.challengeforohub.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {

    Usuario findByDni(int dni);
    Optional<Usuario> findByUserName(String userName);


}
