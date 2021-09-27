package br.com.star.wars.exception;

public class ReporteDeTraicaoDuplicadoException extends RuntimeException {


    public ReporteDeTraicaoDuplicadoException(String reporter, String reportado) {
        super(String.format("O Rebelde %s já reportou traição do Rebelde %s", reporter, reportado));
    }
}
