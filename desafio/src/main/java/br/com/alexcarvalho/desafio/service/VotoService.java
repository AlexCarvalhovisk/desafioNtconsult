package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.ResultadoDTO;
import br.com.alexcarvalho.desafio.dto.VotoDTO;
import br.com.alexcarvalho.desafio.enums.VotoOpcao;
import br.com.alexcarvalho.desafio.model.Sessao;
import br.com.alexcarvalho.desafio.model.Voto;
import br.com.alexcarvalho.desafio.repository.SessaoRepository;
import br.com.alexcarvalho.desafio.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;

    @Autowired
    public VotoService(VotoRepository votoRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public void registrarVoto(VotoDTO votoDTO) {
        Sessao sessao = sessaoRepository.findById(votoDTO.getSessaoId())
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (votoRepository.existsBySessaoIdAndAssociadoId(sessao.getId(), votoDTO.getAssociadoId())) {
            throw new RuntimeException("Associado já votou nesta sessão");
        }

        Voto voto = Voto.builder()
                .sessao(sessao)
                .associadoId(votoDTO.getAssociadoId())
                .votoOpcao(VotoOpcao.valueOf(votoDTO.getVotoOpcao()))
                .build();

        votoRepository.save(voto);
    }

    // Método para contabilizar o resultado da votação
    public ResultadoDTO contabilizarResultado(Long pautaId) {
        // Contabilizando os votos "SIM"
        Long totalSim = votoRepository.countBySessaoIdAndVotoOpcao(pautaId, VotoOpcao.SIM);

        // Contabilizando os votos "NAO"
        Long totalNao = votoRepository.countBySessaoIdAndVotoOpcao(pautaId, VotoOpcao.NAO);

        // Retornando o resultado da pauta
        return new ResultadoDTO(pautaId, totalSim, totalNao);
    }
}


