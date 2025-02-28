package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.PautaDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        pauta = pautaRepository.save(pauta);
        return new PautaDTO(pauta.getId(), pauta.getDescricao(), pauta.getInicio(), pauta.getFim());
    }
}


