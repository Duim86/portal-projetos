package br.com.portaldeprojetos.api.dtos.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjetoModel {

  @ApiModelProperty(example = "1")
  private Long id;

  @ApiModelProperty(example = "Gerenciamento de postos de combustível")
  private String nome;

  @ApiModelProperty(example = "2021-10-05")
  private LocalDate dataInicio;

  @ApiModelProperty(example = "2022-10-05")
  private LocalDate dataPrevisaoFim;

  @ApiModelProperty(example = "2022-12-05")
  private LocalDate dataFim;

  @ApiModelProperty(example = "Software para gerenciamento dos postos de combustível em toda a América Latina")
  private String descricao;

  @ApiModelProperty(example = "EM_ANALISE")
  private String status;

  @ApiModelProperty(example = "50000")
  private BigDecimal orcamento;

  @ApiModelProperty(example = "MEDIO_RISCO")
  private String risco;

  private PessoaResumoModel gerente;

  private List<PessoaResumoModel> membros;
}
