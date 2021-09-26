package br.com.star.wars.exception;

public class PontuacaoNaoEquivalenteException extends RuntimeException {

    public PontuacaoNaoEquivalenteException() {
        super("A pontuação de troca deve ser equivalente!");
    }
}
