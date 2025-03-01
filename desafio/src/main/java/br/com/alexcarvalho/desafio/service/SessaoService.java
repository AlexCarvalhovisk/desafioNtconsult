package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.SessaoDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.model.Sessao;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import br.com.alexcarvalho.desafio.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
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

        try{
            sessao = sessaoRepository.save(sessao);
            log.info("Sessão salva com sucesso!");
        } catch (Exception e){
            log.error("Erro ao criar sessão em abrirSessão no SessãoService", e);
            throw e;
        }
        //verificar o pauta.getId
        return new SessaoDTO(sessao.getId(), pauta.getId(), sessao.getInicio(), sessao.getFim());
    }
}


