package br.com.iaragames.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iaragames.cadastro.model.Jogo;

public interface JogoRepository extends JpaRepository <Jogo, Long> {
    
}
