package br.com.star.wars.service;

import br.com.star.wars.domain.Localizacao;
import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.ReporteDeTraicao;
import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.exception.RebeldeNaoEncontradoException;
import br.com.star.wars.exception.RebeldeTraidorException;
import br.com.star.wars.repository.LocalizacaoRepository;
import br.com.star.wars.repository.RebeldeRepository;
import br.com.star.wars.util.RebeldeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RebeldeServiceTest {

    private RebeldeRepository rebeldeRepository;
    private LocalizacaoRepository localizacaoRepository;
    private RebeldeService rebeldeService;

    @BeforeEach
    void setUp() {
        rebeldeRepository = Mockito.mock(RebeldeRepository.class);
        localizacaoRepository = Mockito.mock(LocalizacaoRepository.class);
        rebeldeService = new RebeldeService(rebeldeRepository, localizacaoRepository);
    }

    @DisplayName("Deve registrar de maneira bem sucedida um rebelde")
    @Test
    void deveRegistrarUmRebeldeCorretamente() {
        RebeldeDTO rebeldeDTO = RebeldeUtils.rebeldeDTO();
        rebeldeService.registrar(rebeldeDTO);

        ArgumentCaptor<Localizacao> localizacaoArgumentCaptor = ArgumentCaptor.forClass(Localizacao.class);
        ArgumentCaptor<Rebelde> rebeldeArgumentCaptor = ArgumentCaptor.forClass(Rebelde.class);

        verify(localizacaoRepository, times(1)).save(localizacaoArgumentCaptor.capture());
        verify(rebeldeRepository, times(1)).save(rebeldeArgumentCaptor.capture());

        assertEquals(rebeldeDTO.getLocalizacao().getBase(), localizacaoArgumentCaptor.getValue().getBase());
        assertEquals(rebeldeDTO.getLocalizacao().getNome(), localizacaoArgumentCaptor.getValue().getNome());
        assertEquals(rebeldeDTO.getLocalizacao().getGalaxia(), localizacaoArgumentCaptor.getValue().getGalaxia());
        assertEquals(rebeldeDTO.getLocalizacao().getLatitude(), localizacaoArgumentCaptor.getValue().getLatitude());
        assertEquals(rebeldeDTO.getLocalizacao().getLongitutde(), localizacaoArgumentCaptor.getValue().getLongitude());

        assertEquals(rebeldeDTO.getNome(), rebeldeArgumentCaptor.getValue().getNome());
        assertEquals(rebeldeDTO.getGenero(), rebeldeArgumentCaptor.getValue().getGenero());
        assertEquals(rebeldeDTO.getIdade(), rebeldeArgumentCaptor.getValue().getIdade());
    }


    @DisplayName("Deve buscar com sucesso um rebelde se ele for valido e não for um traidor")
    @Test
    void deveBuscarComSucesso(){
        Rebelde rebeldeTeste = RebeldeUtils.rebelde();
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(rebeldeTeste));
        Rebelde resultado = rebeldeService.buscar(1L);

        assertEquals(rebeldeTeste.getNome(), resultado.getNome());
        assertEquals(rebeldeTeste.getIdade(), resultado.getIdade());
        assertEquals(rebeldeTeste.getGenero(), resultado.getGenero());
        assertEquals(rebeldeTeste.getPontuacao(), resultado.getPontuacao());
    }


    @DisplayName("Deve retornar erro ao buscar um rebelde traidor")
    @Test
    void deveRetornarErroAoBuscarRebeldeTraidor(){
        Rebelde rebeldeTeste = RebeldeUtils.rebelde();
        rebeldeTeste.getReportesRecebidos().add(new ReporteDeTraicao());
        rebeldeTeste.getReportesRecebidos().add(new ReporteDeTraicao());
        rebeldeTeste.getReportesRecebidos().add(new ReporteDeTraicao());
        rebeldeTeste.seTornouTraidor();
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(rebeldeTeste));

        assertThrows(RebeldeTraidorException.class, ()-> rebeldeService.buscar(1L));
    }

    @DisplayName("Deve retornar erro ao buscar um rebelde não registrado")
    @Test
    void deveRetornarErroAoBuscarRebeldeNaoRegistrado(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RebeldeNaoEncontradoException.class, ()-> rebeldeService.buscar(1L));
    }

}