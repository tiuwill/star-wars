package br.com.star.wars.domain;

import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.util.ItemUtils;
import br.com.star.wars.util.RebeldeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RebeldeTest {

    @DisplayName("Deve verificar e alterar o rebelde se ele tiver se tornado traidor")
    @Test
    void deveAlterarORebelde(){
        Rebelde rebelde = RebeldeUtils.rebelde();
        rebelde.getReportesRecebidos().add(new ReporteDeTraicao());
        rebelde.getReportesRecebidos().add(new ReporteDeTraicao());
        rebelde.getReportesRecebidos().add(new ReporteDeTraicao());

        boolean resultado = rebelde.seTornouTraidor();

        assertTrue(resultado);
        assertTrue(rebelde.isTraidor());
    }

    @DisplayName("Deve verificar e manter o rebelde em seu estado atual se não tiver se tornado um traidor")
    @Test
    void deveManterORebelde(){
        Rebelde rebelde = RebeldeUtils.rebelde();
        boolean resultado = rebelde.seTornouTraidor();

        assertFalse(resultado);
        assertFalse(rebelde.isTraidor());
    }

    @DisplayName("Deve retornar a pontuação do rebelde com base em seus items")
    @Test
    void deveRetornarPontuacao() {
        Rebelde rebelde = RebeldeUtils.rebelde();
        Integer pontuacao = rebelde.getPontuacao();

        assertEquals(10, pontuacao);
    }

    @DisplayName("Deve atualizar o inventário do rebelde")
    @Test
    void deveAtualizarOInventario(){
        Rebelde rebelde = RebeldeUtils.rebelde();
        ItemDTO arma = ItemUtils.criarUnicoItemDTO();
        List<ItemDTO> remover = List.of(arma);
        List<ItemDTO> adicionar = List.of(new ItemDTO(1L, "Água", 4));

        rebelde.atualizarInventario(remover, adicionar);

        assertEquals(4, rebelde.getItems().size());
        assertTrue(rebelde.getItems().stream().noneMatch(item -> item.getDescricao().equals("Arma")));
        assertEquals(2, rebelde.getItems().stream().filter(item -> item.getDescricao().equals("Água")).count());
    }

}