package br.com.portaldeprojetos.api.dtos.model;

import br.com.portaldeprojetos.domain.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ProjetoModel {
  private Long id;

  private String nome;

  private Date dataInicio;

  private LocalDate dataPrevisaoFim;

  private LocalDate dataFim;

  private String descricao;

  private String status;

  private BigDecimal orcamento;

  private String risco;

  private Pessoa idgerente;

}
