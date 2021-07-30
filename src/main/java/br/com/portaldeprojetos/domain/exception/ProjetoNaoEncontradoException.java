package br.com.portaldeprojetos.domain.exception;

public class ProjetoNaoEncontradoException extends RuntimeException{
      public static final long serialVersionUID = 1L;

    public ProjetoNaoEncontradoException(String mensagem) {
      super(mensagem);
    }

    public ProjetoNaoEncontradoException(Long projetoId) {
      this("Não existe um cadastro de projeto com código " + projetoId);
    }
}
