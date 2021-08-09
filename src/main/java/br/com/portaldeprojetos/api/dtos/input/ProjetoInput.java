package br.com.portaldeprojetos.api.dtos.input;

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
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataInicio;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataPrevisaoFim;

  private String descricao;

  private BigDecimal orcamento;

  @NotNull
  @Valid
  private GerenteIdInput gerente;
}
