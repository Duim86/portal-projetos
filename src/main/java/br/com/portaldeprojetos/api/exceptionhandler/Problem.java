package br.com.portaldeprojetos.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {
  private final OffsetDateTime timestamp;

  private final Integer status;

  private final String title;

  private final String detail;

  private final String userMessage;

}
