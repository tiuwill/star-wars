package br.com.star.wars.service;

import br.com.star.wars.domain.Item;
import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.exception.ItemNaoEncontradoException;
import br.com.star.wars.exception.SemItemsParaValidacaoException;
import br.com.star.wars.repository.ItemRepository;
import br.com.star.wars.util.ItemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ItemServiceTest {


    private ItemService itemService;
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository = Mockito.mock(ItemRepository.class);
        itemService = new ItemService(itemRepository);
    }

    @DisplayName("Deve retornar uma lista de items em um set de ItemDTO")
    @Test
    void deveRetornarUmaListaDeItems() {
        Set<Item> items = ItemUtils.criarItemsTeste();
        when(itemRepository.findAll()).thenReturn(items);
        Set<ItemDTO> listar = itemService.listar();

        assertEquals(items.size(), listar.size());
        assertTrue(listar.stream().anyMatch(item -> item.getDescricao().equals("Arma")));
        assertTrue(listar.stream().anyMatch(item -> item.getDescricao().equals("Munição")));
        assertTrue(listar.stream().anyMatch(item -> item.getDescricao().equals("Água")));
        assertTrue(listar.stream().anyMatch(item -> item.getDescricao().equals("Comida")));
    }

    @DisplayName("Deve retornar erro ao tentar checar uma lista vazia de items")
    @Test
    void retornaErroAoChecarListaVazia() {
        assertThrows(SemItemsParaValidacaoException.class, () -> itemService.checkarSeItemsSaoValidos(Collections.emptyList()));
    }

    @DisplayName("Deve retornar erro se um dos items não existir no banco de dados")
    @Test
    void retornarErroSeNaoExistirNoBanco() {
        when(itemRepository.findById(eq(2L))).thenReturn(Optional.empty());
        assertThrows(ItemNaoEncontradoException.class, () ->
                itemService.checkarSeItemsSaoValidos(ItemUtils.criarItemsDTOTeste()));
    }

    @DisplayName("Deve retornar os items caso todos sejam válidos")
    @Test
    void deveRetornarItemsSeTodosSaoValidos(){
        when(itemRepository.findById(any())).thenReturn(Optional.of(ItemUtils.criarUnicoItem()));

        List<ItemDTO> itemDTOS = itemService.checkarSeItemsSaoValidos(List.of(ItemUtils.criarItemsDTOTeste().get(0)));

        assertEquals(1, itemDTOS.size());
        assertEquals("Arma", itemDTOS.get(0).getDescricao());
    }

}