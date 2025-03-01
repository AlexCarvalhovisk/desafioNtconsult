package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.SessaoDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.model.Sessao;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import br.com.alexcarvalho.desafio.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoService sessaoService;

    private SessaoDTO sessaoDTO;
    private Pauta pauta;
    private Sessao sessao;

    @BeforeEach
    void setUp() {
        pauta = new Pauta();
        pauta.setId(1L);

        sessaoDTO = new SessaoDTO(null, 1L, null, null);

        sessao = Sessao.builder()
                .id(1L)
                .pauta(pauta)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(1))
                .build();
    }

    @Test
    void deveAbrirSessaoComSucesso() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

        SessaoDTO resultado = sessaoService.abrirSessao(sessaoDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getPautaId());
        assertNotNull(resultado.getInicio());
        assertNotNull(resultado.getFim());
        verify(sessaoRepository, times(1)).save(any(Sessao.class));
    }

    @Test
    void deveLancarExcecaoQuandoPautaNaoEncontrada() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> sessaoService.abrirSessao(sessaoDTO));

        assertEquals("Pauta não encontrada", exception.getMessage());
        verify(sessaoRepository, never()).save(any(Sessao.class));
    }
}