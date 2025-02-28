package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.PautaDTO;
import br.com.alexcarvalho.desafio.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody PautaDTO pautaDTO) {
        PautaDTO criada = pautaService.criarPauta(pautaDTO);
        return ResponseEntity.ok(criada);
    }
}

