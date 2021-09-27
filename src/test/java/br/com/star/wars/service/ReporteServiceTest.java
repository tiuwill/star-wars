package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.ReporteDeTraicao;
import br.com.star.wars.domain.dto.ReporteDTO;
import br.com.star.wars.exception.AutoReporteException;
import br.com.star.wars.exception.ReporteDeTraicaoDuplicadoException;
import br.com.star.wars.repository.ReporteDeTraicaoRepository;
import br.com.star.wars.util.RebeldeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ReporteServiceTest {

    private ReporteDeTraicaoRepository repository;
    private RebeldeService rebeldeService;
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ReporteDeTraicaoRepository.class);
        rebeldeService = Mockito.mock(RebeldeService.class);
        reporteService = new ReporteService(repository, rebeldeService);
    }

    @DisplayName("Deve dar erro se o rebelde se auto reportar")
    @Test
    void autoReporteErro() {
        ReporteDTO reporteDTO = new ReporteDTO(1L, 1L);
        when(rebeldeService.buscar(any())).thenReturn(RebeldeUtils.rebelde());

        assertThrows(AutoReporteException.class, () -> reporteService.reportar(reporteDTO));
    }

    @DisplayName("Deve dar erro se o rebelde tentar reportar a mesma pessoa mais de uma vez")
    @Test
    void reporteDuplicado() {
        ReporteDTO reporteDTO = new ReporteDTO(1L, 2L);
        when(rebeldeService.buscar(eq(1L))).thenReturn(RebeldeUtils.rebelde());
        when(rebeldeService.buscar(eq(2L))).thenReturn(RebeldeUtils.rebelde(2L));
        when(repository.findByReporterAndReportado(any(), any())).thenReturn(Optional.of(new ReporteDeTraicao()));

        assertThrows(ReporteDeTraicaoDuplicadoException.class, () -> reporteService.reportar(reporteDTO));
    }

    @DisplayName("Deve reportar de maneira bem sucedida mas sem se tornar traidor")
    @Test
    void reporteBemSucedido() {
        ReporteDTO reporteDTO = new ReporteDTO(1L, 2L);
        when(rebeldeService.buscar(eq(1L))).thenReturn(RebeldeUtils.rebelde());
        when(rebeldeService.buscar(eq(2L))).thenReturn(RebeldeUtils.rebelde(2L));
        when(repository.findByReporterAndReportado(any(), any())).thenReturn(Optional.empty());

        reporteService.reportar(reporteDTO);
        verify(repository, times(1)).save(any());
        verify(rebeldeService, times(0)).salvar(any());
    }

    @DisplayName("Deve reportar de maneira bem sucedida mas e se tornou um traidor")
    @Test
    void reporteBemSucedidoViroutraidor() {
        ReporteDTO reporteDTO = new ReporteDTO(1L, 2L);

        Rebelde traidor = RebeldeUtils.rebelde(2L);
        traidor.getReportesRecebidos().add(new ReporteDeTraicao());
        traidor.getReportesRecebidos().add(new ReporteDeTraicao());
        traidor.getReportesRecebidos().add(new ReporteDeTraicao());
        when(rebeldeService.buscar(eq(1L))).thenReturn(RebeldeUtils.rebelde());
        when(rebeldeService.buscar(eq(2L))).thenReturn(traidor);
        when(repository.findByReporterAndReportado(any(), any())).thenReturn(Optional.empty());

        reporteService.reportar(reporteDTO);
        verify(repository, times(1)).save(any());
        verify(rebeldeService, times(1)).salvar(any());
    }

}