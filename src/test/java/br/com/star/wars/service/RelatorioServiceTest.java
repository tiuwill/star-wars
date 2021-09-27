package br.com.star.wars.service;

import br.com.star.wars.domain.dto.relatorio.PontosRelatorioDTO;
import br.com.star.wars.domain.dto.relatorio.RebeldesRelatoriosDTO;
import br.com.star.wars.domain.dto.relatorio.TraidoresRelatorioDTO;
import br.com.star.wars.util.RebeldeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RelatorioServiceTest {

    private RebeldeService rebeldeService;
    private RelatorioService relatorioService;

    @BeforeEach
    void setUp() {
       rebeldeService = Mockito.mock(RebeldeService.class);
       relatorioService = new RelatorioService(rebeldeService);
    }

    @DisplayName("Deve retornar a procentagem de traidores")
    @Test
    void porcentagemTraidores(){
        when(rebeldeService.contarTraidores()).thenReturn(100L);
        when(rebeldeService.totalDeRegistros()).thenReturn(1000L);

        TraidoresRelatorioDTO traidoresRelatorioDTO = relatorioService.calcularTraidores();

        assertEquals("10,00", traidoresRelatorioDTO.getTraidores());
    }

    @DisplayName("Deve retornar a procentagem de rebeldes")
    @Test
    void porcentagemRebeldes(){
        when(rebeldeService.contarRebeldes()).thenReturn(100L);
        when(rebeldeService.totalDeRegistros()).thenReturn(1000L);

        RebeldesRelatoriosDTO rebeldesRelatoriosDTO = relatorioService.calcularRebeldes();

        assertEquals("10,00", rebeldesRelatoriosDTO.getRebeldes());
    }

    @DisplayName("Deve retornar a quantidade de pontos perdidos")
    @Test
    void quantiadeDePontosPerdidos(){
        when(rebeldeService.listarTraidores()).thenReturn(List.of(RebeldeUtils.rebelde(), RebeldeUtils.rebelde()));
        PontosRelatorioDTO pontosRelatorioDTO = relatorioService.calcularPontos();

        assertEquals(20, pontosRelatorioDTO.getPontosPerdidos());
    }

}