package com.backend.challengeforohub.repository;

import com.backend.challengeforohub.entity.Topico;
import com.backend.challengeforohub.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositoriy extends JpaRepository<Topico, Long> {
    Topico findByTitulo(String titulo);
}
