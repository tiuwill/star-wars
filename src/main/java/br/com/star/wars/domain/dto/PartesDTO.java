package br.com.star.wars.domain.dto;


import java.util.List;

public class PartesDTO {

    private NegocianteDTO negociante;
    private List<ItemDTO> items;

    public PartesDTO(NegocianteDTO negociante, List<ItemDTO> items) {
        this.negociante = negociante;
        this.items = items;
    }

    public NegocianteDTO getNegociante() {
        return negociante;
    }

    public List<ItemDTO> getItems() {
        return items;
    }
}
