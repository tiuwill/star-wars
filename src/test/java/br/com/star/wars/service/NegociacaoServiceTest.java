package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.dto.NegociacaoDTO;
import br.com.star.wars.domain.dto.NegocianteDTO;
import br.com.star.wars.util.NegociacaoUtils;
import br.com.star.wars.util.RebeldeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NegociacaoServiceTest {

    private NegociacaoService negociacaoService;
    private RebeldeService rebeldeService;
    private RegraDeNegociacaoService regraDeNegociacaoService;

    @BeforeEach
    void setUp() {
        rebeldeService = Mockito.mock(RebeldeService.class);
        regraDeNegociacaoService = Mockito.mock(RegraDeNegociacaoService.class);
        negociacaoService = new NegociacaoService(rebeldeService,regraDeNegociacaoService);
    }


    @DisplayName("Deve executar fluxo de negociação, buscar rebeldes, validar, efetivar a troca")
    @Test
    void deveExecutarFluxoDeNegociacao() {
        NegociacaoDTO negociacaoDTO = NegociacaoUtils.criarNegociacaoTeste();
        when(rebeldeService.buscar(any())).thenReturn(RebeldeUtils.rebelde());

        negociacaoService.negociar(negociacaoDTO);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(rebeldeService, times(2)).buscar(idCaptor.capture());
        verify(regraDeNegociacaoService, times(1)).validarNegociacao(anyList(), anyList());
        verify(rebeldeService, times(2)).salvar(any(Rebelde.class));


        assertEquals(negociacaoDTO.getIdPrimieroNegociante(), idCaptor.getAllValues().get(0));
        assertEquals(negociacaoDTO.getIdSegundoNegociante(), idCaptor.getAllValues().get(1));

    }

}