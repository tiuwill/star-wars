package br.com.star.wars.util;

import br.com.star.wars.domain.Genero;
import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.dto.RebeldeDTO;

import java.util.ArrayList;
import java.util.Collections;

public class RebeldeUtils {

    public static Rebelde rebelde(){
        return new Rebelde(
                1L,
                "Rebelde um",
                22,
                Genero.FEMININO,
                new ArrayList<>(ItemUtils.criarItemsTeste()),
                LocalizacaoUtils.criarLocalizacaoTeste(),
                new ArrayList<>());
    }

    public static Rebelde rebelde(Long id){
        return new Rebelde(
                id,
                "Rebelde um",
                22,
                Genero.FEMININO,
                new ArrayList<>(ItemUtils.criarItemsTeste()),
                LocalizacaoUtils.criarLocalizacaoTeste(),
                new ArrayList<>());
    }

    public static RebeldeDTO rebeldeDTO() {
        return RebeldeDTO.of(rebelde());
    }


}
