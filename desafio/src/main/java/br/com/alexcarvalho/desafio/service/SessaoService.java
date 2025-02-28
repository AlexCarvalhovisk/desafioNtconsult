package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.SessaoDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.model.Sessao;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import br.com.alexcarvalho.desafio.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public SessaoDTO abrirSessao(SessaoDTO sessaoDTO) {
        Pauta pauta = pautaRepository.findById(sessaoDTO.getPautaId())
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        Sessao sessao = Sessao.builder()
                .pauta(pauta)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(1)) // Tempo padrão de 1 minuto
                .build();

        sessao = sessaoRepository.save(sessao);
        return new SessaoDTO(sessao.getId(), pauta.getId(), sessao.getInicio(), sessao.getFim());
    }
}


