package br.com.portaldeprojetos.api.dtos.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProjetoInput {

  @NotBlank
  @ApiModelProperty(example = "Gerenciamento de Hotéis", required = true)
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(example = "2022-01-11")
  private LocalDate dataInicio;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(example = "2022-12-11")
  private LocalDate dataPrevisaoFim;

  @ApiModelProperty(example = "Software para o controle de hospédes")
  private String descricao;

  @ApiModelProperty(example = "100000000")
  private BigDecimal orcamento;

  @NotNull
  @Valid
  private GerenteIdInput gerente;
}
