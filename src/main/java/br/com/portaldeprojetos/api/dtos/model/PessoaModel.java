package br.com.portaldeprojetos.api.dtos.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaModel {

  @ApiModelProperty(example = "1")
  private Long id;

  @ApiModelProperty(example = "Bruno Henrique")
  private String nome;

  @ApiModelProperty(example = "1979-10-05")
  private LocalDate dataNascimento;

  @ApiModelProperty(example = "67738802898")
  private String cpf;

  @ApiModelProperty(example = "true")
  private Boolean funcionario;

  private List<ProjetoListModel> projetos;

}
