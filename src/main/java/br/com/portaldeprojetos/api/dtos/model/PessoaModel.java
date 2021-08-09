package br.com.portaldeprojetos.api.dtos.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaModel {
  private Long id;

  private String nome;

  private LocalDate dataNascimento;

  private String cpf;

  private Boolean funcionario;

  private List<ProjetoListModel> projetos;

}
