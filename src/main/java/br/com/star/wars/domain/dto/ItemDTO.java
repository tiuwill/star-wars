package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.Item;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemDTO {

    @NotNull
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

    public static int calcularPontuacao(List<ItemDTO> itemsPrimeiroNegociante) {
        return itemsPrimeiroNegociante.stream().mapToInt(ItemDTO::getPontos).sum();
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
