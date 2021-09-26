package br.com.star.wars.controller;

import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Cacheable(value = "listaItems")
    public ResponseEntity<Set<ItemDTO>> listar() {
        Set<ItemDTO> items = itemService.listar();
        return ResponseEntity.ok(items);
    }
}
