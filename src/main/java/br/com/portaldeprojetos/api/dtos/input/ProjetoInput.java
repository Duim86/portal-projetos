package br.com.portaldeprojetos.api.dtos.input;

import br.com.portaldeprojetos.domain.model.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ProjetoInput {

  @NotBlank
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dataInicio;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataPrevisaoFim;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataFim;

  private String descricao;

  private String status;

  private BigDecimal orcamento;

  private String risco;

  private Pessoa idgerente;
}
