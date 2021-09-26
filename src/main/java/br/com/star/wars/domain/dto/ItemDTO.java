package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.Item;

public class ItemDTO {

    private Long id;
    private String descricao;
    private Integer pontos;

    public ItemDTO(Long id, String descricao, Integer pontos) {
        this.id = id;
        this.descricao = descricao;
        this.pontos = pontos;
    }

    public static ItemDTO of(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getDescricao(),
                item.getPontos()
        );
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPontos() {
        return pontos;
    }

}
