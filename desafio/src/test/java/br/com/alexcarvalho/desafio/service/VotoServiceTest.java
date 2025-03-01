package br.com.alexcarvalho.desafio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.alexcarvalho.desafio.dto.ResultadoDTO;
import br.com.alexcarvalho.desafio.dto.VotoDTO;
import br.com.alexcarvalho.desafio.enums.VotoOpcao;
import br.com.alexcarvalho.desafio.model.Sessao;
import br.com.alexcarvalho.desafio.model.Voto;
import br.com.alexcarvalho.desafio.repository.SessaoRepository;
import br.com.alexcarvalho.desafio.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoRepository sessaoRepository;

    @InjectMocks
    private VotoService votoService;

    private VotoDTO votoDTO;
    private Sessao sessao;

    @BeforeEach
    void setUp() {
        votoDTO = new VotoDTO(1L, 1L, "SIM");
        sessao = new Sessao();
        sessao.setId(1L);
    }

    @Test
    void registrarVoto_ComSucesso() {
        when(sessaoRepository.findById(votoDTO.getSessaoId())).thenReturn(Optional.of(sessao));
        when(votoRepository.existsBySessaoIdAndAssociadoId(sessao.getId(), votoDTO.getAssociadoId())).thenReturn(false);

        assertDoesNotThrow(() -> votoService.registrarVoto(votoDTO));
        verify(votoRepository, times(1)).save(any(Voto.class));
    }

    @Test
    void registrarVoto_DeveLancarExcecao_SeSessaoNaoExistir() {
        when(sessaoRepository.findById(votoDTO.getSessaoId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> votoService.registrarVoto(votoDTO));
        assertEquals("Sessão não encontrada", exception.getMessage());
    }

    @Test
    void registrarVoto_DeveLancarExcecao_SeAssociadoJaVotou() {
        when(sessaoRepository.findById(votoDTO.getSessaoId())).thenReturn(Optional.of(sessao));
        when(votoRepository.existsBySessaoIdAndAssociadoId(sessao.getId(), votoDTO.getAssociadoId())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> votoService.registrarVoto(votoDTO));
        assertEquals("Associado já votou nesta sessão", exception.getMessage());
    }

    @Test
    void contabilizarResultado_DeveRetornarResultadoCorreto() {
        Long pautaId = 1L;
        when(votoRepository.countBySessaoIdAndVotoOpcao(pautaId, VotoOpcao.SIM)).thenReturn(5L);
        when(votoRepository.countBySessaoIdAndVotoOpcao(pautaId, VotoOpcao.NAO)).thenReturn(3L);

        ResultadoDTO resultado = votoService.contabilizarResultado(pautaId);

        assertEquals(pautaId, resultado.getPautaId());
        assertEquals(5L, resultado.getTotalSim());
        assertEquals(3L, resultado.getTotalNao());
    }
}
