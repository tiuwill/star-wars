package br.com.star.wars.exception;

public class SemItemsParaValidacaoException extends RuntimeException {

    public SemItemsParaValidacaoException() {
        super("Nenhum item foi passado para checagem");
    }
}
