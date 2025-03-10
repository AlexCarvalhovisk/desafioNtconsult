package br.com.alexcarvalho.desafio.repository;

import br.com.alexcarvalho.desafio.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}

