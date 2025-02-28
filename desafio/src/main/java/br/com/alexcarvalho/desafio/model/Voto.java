package br.com.alexcarvalho.desafio.model;

import br.com.alexcarvalho.desafio.enums.VotoOpcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    private Long associadoId;

    @Enumerated(EnumType.STRING)
    private VotoOpcao votoOpcao;
}

