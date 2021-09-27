package br.com.star.wars.exception;

public class AutoReporteException extends RuntimeException {

    public AutoReporteException(String nome) {
        super(String.format("O Rebelde %s tentou se reportar como traídor", nome));
    }
}
