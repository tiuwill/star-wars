package br.com.star.wars.domain.dto.relatorio;

import java.util.Map;

public class RecursosRelatorioDTO {

    Map<String, Double> dados;

    public RecursosRelatorioDTO(Map<String, Double> dados) {
        this.dados = dados;
    }

    public Map<String, Double> getDados() {
        return dados;
    }
}
