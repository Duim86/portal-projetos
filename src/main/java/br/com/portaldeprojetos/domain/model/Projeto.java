package br.com.portaldeprojetos.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Projeto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataInicio;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataPrevisaoFim;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dataFim;

  private String descricao;

  @Enumerated(EnumType.STRING)
  private Status status;

  private BigDecimal orcamento;

  @Enumerated(EnumType.STRING)
  private Risco risco;

  @OneToOne
  @JoinColumn(name = "idgerente")
  private Pessoa idgerente;

  @ManyToMany
  @JoinTable(
          name = "membros",
          joinColumns = @JoinColumn(name = "idprojeto"),
          inverseJoinColumns = @JoinColumn(name = "idpessoa")
  )
  Set<Pessoa> membros;

  public void adicionarPessoa(Pessoa pessoa) {
    getMembros().add(pessoa);
  }
}
