package br.com.star.wars.exception;

public class RebeldeNaoEncontradoException extends RuntimeException {

    public RebeldeNaoEncontradoException() {
        super("Rebelde não encontrado");
    }
}
