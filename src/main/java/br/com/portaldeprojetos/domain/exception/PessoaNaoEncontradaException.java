package br.com.portaldeprojetos.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNaoEncontradaException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  public PessoaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public PessoaNaoEncontradaException(Long pessoaId) {
    this("Não existe um cadastro de projeto com código " + pessoaId);
  }
}
