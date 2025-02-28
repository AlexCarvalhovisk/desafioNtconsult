package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.PautaDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public PautaDTO criarPauta(PautaDTO pautaDTO) {
        Pauta pauta = Pauta.builder()
                .descricao(pautaDTO.getDescricao())
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(1)) // 1 minuto por padrão
                .build();
        try {
            pauta = pautaRepository.save(pauta);
            log.info("Pauta salva com sucesso!");
        } catch (Exception e) {
            log.error("Erro ao criar pauta em criarPauta no PautaService", e);
            throw e;
        }
        return new PautaDTO(pauta.getId(), pauta.getDescricao(), pauta.getInicio(), pauta.getFim());
    }
}


