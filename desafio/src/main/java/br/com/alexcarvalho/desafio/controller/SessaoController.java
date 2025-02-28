package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.SessaoDTO;
import br.com.alexcarvalho.desafio.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoDTO> abrirSessao(@RequestBody SessaoDTO sessaoDTO) {
        SessaoDTO criada = sessaoService.abrirSessao(sessaoDTO);
        return ResponseEntity.ok(criada);
    }
}


