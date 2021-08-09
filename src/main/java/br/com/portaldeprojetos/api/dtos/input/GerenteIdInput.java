package br.com.portaldeprojetos.api.dtos.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GerenteIdInput {

  @NotNull
  @ApiModelProperty(example = "1")
  private Long id;
}
