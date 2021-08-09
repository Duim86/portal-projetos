package br.com.portaldeprojetos.api.dtos.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjetoModel {
  private Long id;

  private String nome;

  private LocalDate dataInicio;

  private LocalDate dataPrevisaoFim;

  private LocalDate dataFim;

  private String descricao;

  private String status;

  private BigDecimal orcamento;

  private String risco;

  private PessoaResumoModel gerente;

  private List<PessoaResumoModel> membros;
}
