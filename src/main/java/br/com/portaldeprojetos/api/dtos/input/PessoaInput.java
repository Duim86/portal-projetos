package br.com.portaldeprojetos.api.dtos.input;

import io.swagger.annotations.ApiModelProperty;
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
  @ApiModelProperty(example = "Everton Ribeiro", required = true)
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty(example = "1986-01-11")
  private LocalDate dataNascimento;

  @CPF
  @ApiModelProperty(example = "67738802898")
  private String cpf;

  @ApiModelProperty(example = "true")
  private Boolean funcionario;
}
