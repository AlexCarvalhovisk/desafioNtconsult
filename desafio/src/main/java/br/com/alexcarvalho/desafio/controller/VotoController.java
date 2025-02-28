package br.com.alexcarvalho.desafio.controller;

import br.com.alexcarvalho.desafio.dto.ResultadoDTO;
import br.com.alexcarvalho.desafio.dto.VotoDTO;
import br.com.alexcarvalho.desafio.service.VotoService;
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

    @PostMapping
    public ResponseEntity<Void> registrarVoto(@RequestBody VotoDTO votoDTO) {
        votoService.registrarVoto(votoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/resultados/{pautaId}")
    public ResponseEntity<ResultadoDTO> consultarResultado(@PathVariable Long pautaId) {
        ResultadoDTO resultado = votoService.contabilizarResultado(pautaId);
        return ResponseEntity.ok(resultado);
    }
}




