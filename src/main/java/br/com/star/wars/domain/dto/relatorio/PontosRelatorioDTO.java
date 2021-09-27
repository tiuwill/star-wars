package br.com.star.wars.domain.dto.relatorio;

public class PontosRelatorioDTO {

    private Integer pontosPerdidos;

    public PontosRelatorioDTO(Integer pontosPerdidos) {
        this.pontosPerdidos = pontosPerdidos;
    }

    public Integer getPontosPerdidos() {
        return pontosPerdidos;
    }
}
