package br.com.star.wars.util;

import br.com.star.wars.domain.Genero;
import br.com.star.wars.domain.Rebelde;

import java.util.ArrayList;

public class RebeldeUtils {

    public static Rebelde rebelde(){
        return new Rebelde("Rebelde um",
                22,
                Genero.FEMININO,
                new ArrayList<>(ItemUtils.criarItemsTeste()),
                LocalizacaoUtils.criarLocalizacaoTeste());
    }


}
