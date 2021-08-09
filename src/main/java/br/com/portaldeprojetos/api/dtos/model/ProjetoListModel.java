package br.com.portaldeprojetos.api.dtos.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjetoListModel {
  private Long id;

  private String nome;

  private LocalDate dataInicio;

  private LocalDate dataPrevisaoFim;

  private String nomeGerente;
}
