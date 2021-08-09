package br.com.portaldeprojetos.api.dtos.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResumoModel {

  @ApiModelProperty(example = "1")
  private Long id;

  @ApiModelProperty(example = "Bruno Henrique")
  private String nome;
}
