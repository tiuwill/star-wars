package br.com.star.wars.domain.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NegociacaoDTO {

    @NotNull
    private PartesDTO primeiraParte;
    @NotNull
    private PartesDTO segundaParte;

    public NegociacaoDTO(PartesDTO primeiraParte, PartesDTO segundaParte) {
        this.primeiraParte = primeiraParte;
        this.segundaParte = segundaParte;
    }

    public Long getIdPrimieroNegociante(){
        return this.primeiraParte.getNegociante().getId();
    }

    public List<ItemDTO> getItemsPrimeiroNegociante(){
        return this.primeiraParte.getItems();
    }

    public Long getIdSegundoNegociante(){
        return this.segundaParte.getNegociante().getId();
    }

    public List<ItemDTO> getItemsSegundoNegociante(){
        return this.segundaParte.getItems();
    }
}
