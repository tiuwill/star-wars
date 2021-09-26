package br.com.star.wars.exception;

public class RebeldeTraidorException extends RuntimeException {

    public RebeldeTraidorException(String rebelde) {
        super(String.format("Não é possível executar operações com o rebelde {} pois é um traídor", rebelde));
    }
}
