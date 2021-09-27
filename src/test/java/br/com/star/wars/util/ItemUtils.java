package br.com.star.wars.util;

import br.com.star.wars.domain.Item;
import br.com.star.wars.domain.dto.ItemDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemUtils {

    public static Set<Item> criarItemsTeste() {
        Item arma = new Item(1L, "Arma", 4);
        Item municao = new Item(2L, "Munição", 3);
        Item agua = new Item(3L, "Água", 2);
        Item comida = new Item(4L, "Comida", 1);

        return Set.of(arma, municao, agua, comida);
    }

    public static ItemDTO criarUnicoItemDTO(){
        return new ItemDTO(1L, "Arma", 4);
    }

    public static Item criarUnicoItem(){
        return new Item(1L, "Arma", 4);
    }



    public static List<ItemDTO> criarItemsDTOTeste() {
        return criarItemsTeste().stream().map(ItemDTO::of).collect(Collectors.toList());
    }

}
