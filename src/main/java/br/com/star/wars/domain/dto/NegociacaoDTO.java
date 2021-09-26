package br.com.star.wars.domain.dto;

import java.util.List;

public class NegociacaoDTO {

    private PartesDTO parte1;
    private PartesDTO parte2;

    public Long getIdPrimieroNegociante(){
        return this.parte1.getNegociante().getId();
    }

    public List<ItemDTO> getItemsPrimeiroNegociante(){
        return this.parte1.getItems();
    }

    public Long getIdSegundoNegociante(){
        return this.parte2.getNegociante().getId();
    }

    public List<ItemDTO> getItemsSegundoNegociante(){
        return this.parte2.getItems();
    }
}
