package br.com.star.wars.service;

import br.com.star.wars.domain.Item;
import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.exception.ItemNaoEncontradoException;
import br.com.star.wars.exception.SemItemsParaValidacaoException;
import br.com.star.wars.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Set<ItemDTO> listar() {
        return itemRepository.findAll().stream().map(ItemDTO::of).collect(Collectors.toSet());
    }

    public List<ItemDTO> checkarSeItemsSaoValidos(List<ItemDTO> items) {
        if(items.isEmpty()) throw new SemItemsParaValidacaoException();
        final List<ItemDTO> itemsValidos = new ArrayList<>();
        items.forEach(item -> itemsValidos.add(
                ItemDTO.of(itemRepository.findById(item.getId()).orElseThrow(ItemNaoEncontradoException::new)))
        );
        return itemsValidos;
    }
}
