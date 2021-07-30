package br.com.portaldeprojetos.api.dtos.input;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaInput {

  @NotBlank
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate datanascimento;

  @CPF
  private String cpf;

  private Boolean funcionario;
}
