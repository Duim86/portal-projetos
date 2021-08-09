package br.com.portaldeprojetos.api.dtos.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjetoListModel {

  @ApiModelProperty(example = "1")
  private Long id;

  @ApiModelProperty(example = "Programa para postos de combustível")
  private String nome;

  @ApiModelProperty(example = "2021-10-05")
  private LocalDate dataInicio;

  @ApiModelProperty(example = "2022-10-05")
  private LocalDate dataPrevisaoFim;

  @ApiModelProperty(example = "Gabriel Barbosa")
  private String nomeGerente;
}
