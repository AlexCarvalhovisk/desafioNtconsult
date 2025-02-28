package br.com.alexcarvalho.desafio.repository;

import br.com.alexcarvalho.desafio.enums.VotoOpcao;
import br.com.alexcarvalho.desafio.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsBySessaoIdAndAssociadoId(Long sessaoId, Long associadoId);

    Long countBySessaoIdAndVotoOpcao(Long sessaoId, VotoOpcao votoOpcao);
}


