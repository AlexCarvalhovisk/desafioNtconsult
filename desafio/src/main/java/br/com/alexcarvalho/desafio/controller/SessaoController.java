package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.SessaoDTO;
import br.com.alexcarvalho.desafio.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Abre uma nova sessão de votação",
            description = "Recebe um objeto SessaoDTO e retorna a sessão criada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão aberta com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados são inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!")
    })

    @PostMapping
    public ResponseEntity<SessaoDTO> abrirSessao(@RequestBody SessaoDTO sessaoDTO) {
        SessaoDTO criada = sessaoService.abrirSessao(sessaoDTO);
        return ResponseEntity.ok(criada);
    }
}


