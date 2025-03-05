package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.PautaDTO;
import br.com.alexcarvalho.desafio.service.PautaService;
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
@RequestMapping("/api/pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Operation(summary = "Cria uma nova pauta", description = "Recebe um objeto PautaDTO e retorna a pauta criada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados são inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor!")
    })

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody PautaDTO pautaDTO) {
        PautaDTO criada = pautaService.criarPauta(pautaDTO);
        return ResponseEntity.ok(criada);
    }
}

