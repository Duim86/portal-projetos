package br.com.portaldeprojetos.api.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GerenteIdInput {

  @NotNull
  private Long idgerente;
}
