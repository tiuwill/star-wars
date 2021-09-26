package br.com.star.wars.exception;

public class ReporteDeTraicaoDuplicadoException extends RuntimeException {


    public ReporteDeTraicaoDuplicadoException(String reporter, String reportado) {
        super(String.format("O Rebelde {} já reportou traição do Rebelde {}", reporter, reportado));
    }
}
