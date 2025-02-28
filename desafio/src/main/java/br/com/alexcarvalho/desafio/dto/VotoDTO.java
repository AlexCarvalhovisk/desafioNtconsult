package br.com.alexcarvalho.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoDTO {

    private Long associadoId;
    private Long sessaoId;
    private String votoOpcao; // "SIM" ou "NAO"
}


