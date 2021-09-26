package br.com.star.wars.exception;

public class ItemNaoEncontradoException extends RuntimeException {

    public ItemNaoEncontradoException() {
        super("Item passado não foi encontrado");
    }
}
