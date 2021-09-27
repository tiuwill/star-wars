package br.com.star.wars.service;

import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.exception.PontuacaoNaoEquivalenteException;
import br.com.star.wars.util.ItemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class RegraDeNegociacaoServiceTest {

    private ItemService itemService;
    private RegraDeNegociacaoService regraDeNegociacaoService;


    @BeforeEach
    void setUp() {
        itemService = Mockito.mock(ItemService.class);
        regraDeNegociacaoService = new RegraDeNegociacaoService(itemService);
    }

    @DisplayName("Deve validar com sucesso as etapas da negociação")
    @Test
    void deveValidarComSucessoANegociacao() {
        List<ItemDTO> itemsPrimeiroNegociante = ItemUtils.criarItemsDTOTeste();
        List<ItemDTO> itemsSegundoNegociante = ItemUtils.criarItemsDTOTeste();

        regraDeNegociacaoService.validarNegociacao(itemsPrimeiroNegociante, itemsSegundoNegociante);
        verify(itemService, times(2)).checkarSeItemsSaoValidos(anyList());
    }

    @DisplayName("Deve apresentar erro caso a pontuação dos items não sejam equivalentes")
    @Test
    void deveValidarComErro() {
        List<ItemDTO> itemsPrimeiroNegociante = ItemUtils.criarItemsDTOTeste();
        List<ItemDTO> itemsSegundoNegociante = ItemUtils.criarItemsDTOTeste();
        itemsSegundoNegociante.remove(0);

        when(itemService.checkarSeItemsSaoValidos(eq(itemsPrimeiroNegociante))).thenReturn(itemsPrimeiroNegociante);
        when(itemService.checkarSeItemsSaoValidos(eq(itemsSegundoNegociante))).thenReturn(itemsSegundoNegociante);


        assertThrows(PontuacaoNaoEquivalenteException.class, () ->
                regraDeNegociacaoService.validarNegociacao(itemsPrimeiroNegociante, itemsSegundoNegociante));
    }
}