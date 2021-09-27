package br.com.star.wars.util;

import br.com.star.wars.domain.Localizacao;

public class LocalizacaoUtils {

    public static Localizacao criarLocalizacaoTeste(){
        return new Localizacao(1L, 0.0, 0.5, "Longe", "Nada", "Sem base");
    }
}
