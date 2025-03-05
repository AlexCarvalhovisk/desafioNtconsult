package br.com.alexcarvalho.desafio.repository;

import br.com.alexcarvalho.desafio.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
}
