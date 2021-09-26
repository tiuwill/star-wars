package br.com.star.wars.domain.dto;


import java.util.List;

public class PartesDTO {

    private NegocianteDTO negociante;
    private List<ItemDTO> items;

    public NegocianteDTO getNegociante() {
        return negociante;
    }

    public List<ItemDTO> getItems() {
        return items;
    }
}
