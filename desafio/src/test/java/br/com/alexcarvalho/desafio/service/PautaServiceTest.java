package br.com.alexcarvalho.desafio.service;

import br.com.alexcarvalho.desafio.dto.PautaDTO;
import br.com.alexcarvalho.desafio.model.Pauta;
import br.com.alexcarvalho.desafio.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    private PautaDTO pautaDTO;
    private Pauta pauta;

    @BeforeEach
    void setUp() {
        pautaDTO = new PautaDTO(null, "Pauta Teste", null, null);
        pauta = Pauta.builder()
                .id(1L)
                .descricao("Pauta Teste")
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(1))
                .build();
    }

    @Test
    void deveCriarPautaComSucesso() {
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        PautaDTO resultado = pautaService.criarPauta(pautaDTO);

        assertNotNull(resultado);
        assertEquals(pauta.getId(), resultado.getId());
        assertEquals(pauta.getDescricao(), resultado.getDescricao());
        assertNotNull(resultado.getInicio());
        assertNotNull(resultado.getFim());

        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarPauta() {
        when(pautaRepository.save(any(Pauta.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> pautaService.criarPauta(pautaDTO));

        assertEquals("Erro ao salvar", exception.getMessage());
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }
}