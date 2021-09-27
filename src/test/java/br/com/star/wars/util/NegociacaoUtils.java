package br.com.star.wars.util;

import br.com.star.wars.domain.dto.NegociacaoDTO;
import br.com.star.wars.domain.dto.NegocianteDTO;
import br.com.star.wars.domain.dto.PartesDTO;

public class NegociacaoUtils {

    public static NegociacaoDTO criarNegociacaoTeste(){
        PartesDTO parte1 = new PartesDTO(criarNegocianteTeste(1L), ItemUtils.criarItemsDTOTeste());
        PartesDTO parte2 = new PartesDTO(criarNegocianteTeste(2L), ItemUtils.criarItemsDTOTeste());

        return new NegociacaoDTO(parte1, parte2);
    }

    public static NegocianteDTO criarNegocianteTeste(Long id){
        NegocianteDTO negocianteDTO = new NegocianteDTO();
        negocianteDTO.setId(id);
        return negocianteDTO;
    }


}
