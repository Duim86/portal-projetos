package br.com.portaldeprojetos.api.dtos.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PessoaModel {
  private Long id;

  private String nome;

  private Date datanascimento;

  private String cpf;

  private Boolean funcionario;

}
