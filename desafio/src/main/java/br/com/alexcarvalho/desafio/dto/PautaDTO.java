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
public class PautaDTO {

    private Long id;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}

