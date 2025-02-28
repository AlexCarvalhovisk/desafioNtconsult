package br.com.alexcarvalho.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoDTO {

    private Long pautaId;
    private Long totalSim;
    private Long totalNao;
}

