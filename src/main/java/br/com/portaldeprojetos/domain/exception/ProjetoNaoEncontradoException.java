package br.com.portaldeprojetos.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjetoNaoEncontradoException extends EntidadeNaoEncontradaException{
      public static final long serialVersionUID = 1L;

    public ProjetoNaoEncontradoException(String mensagem) {
      super(mensagem);
    }

    public ProjetoNaoEncontradoException(Long projetoId) {
      this("Não existe um cadastro de projeto com código " + projetoId);
    }
}
