package br.com.portaldeprojetos.domain.exception;

public class PessoaNaoEncontradaException extends RuntimeException{
      public static final long serialVersionUID = 1L;

    public PessoaNaoEncontradaException(String mensagem) {
      super(mensagem);
    }

    public PessoaNaoEncontradaException(Long pessoaId) {
      this("Não existe um cadastro de projeto com código " + pessoaId);
    }
}
