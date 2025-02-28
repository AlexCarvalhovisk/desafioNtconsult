package br.com.alexcarvalho.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoDTO {

    private Long id;
    private Long pautaId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}
