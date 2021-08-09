package br.com.portaldeprojetos.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
  public static final long serialVersionUID = 1L;

  protected EntidadeNaoEncontradaException(String mensagem) {
    super(mensagem);
  }
}