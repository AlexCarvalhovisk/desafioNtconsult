package br.com.alexcarvalho.desafio.repository;

import br.com.alexcarvalho.desafio.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
