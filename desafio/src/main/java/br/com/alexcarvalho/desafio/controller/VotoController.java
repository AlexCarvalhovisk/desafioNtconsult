package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.ResultadoDTO;
import br.com.alexcarvalho.desafio.dto.VotoDTO;
import br.com.alexcarvalho.desafio.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @Operation(summary = "Registra um voto",
            description = "Recebe um objeto VotoDTO e registra o voto na pauta correspondente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto registrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Os dados são inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    @PostMapping
    public ResponseEntity<Void> registrarVoto(@RequestBody VotoDTO votoDTO) {
        votoService.registrarVoto(votoDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Consulta o resultado da votação",
            description = "Retorna o resultado da votação de uma pauta específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado da votação retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/resultados/{pautaId}")
    public ResponseEntity<ResultadoDTO> consultarResultado(
            @Parameter(description = "ID da pauta a ser consultada", example = "1")
            @PathVariable Long pautaId) {
        ResultadoDTO resultado = votoService.contabilizarResultado(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
