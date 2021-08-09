package br.com.portaldeprojetos.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
  DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
  ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

  private final String title;

  ProblemType(String path, String title) {
    this.title = title;
  }
}
