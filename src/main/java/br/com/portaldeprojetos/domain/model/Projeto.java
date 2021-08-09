package br.com.portaldeprojetos.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projeto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String nome;

  private LocalDate dataInicio;

  private LocalDate dataPrevisaoFim;

  private LocalDate dataFim;

  private String descricao;

  @Enumerated(EnumType.STRING)
  private Status status = Status.EM_ANALISE;

  private BigDecimal orcamento;

  @Enumerated(EnumType.STRING)
  private Risco risco;

  @OneToOne
  @JoinColumn(name = "idgerente")
  private Pessoa gerente;

  @ManyToMany
  @JoinTable(
          name = "membros",
          joinColumns = @JoinColumn(name = "idprojeto"),
          inverseJoinColumns = @JoinColumn(name = "idpessoa")
  )
  private Set<Pessoa> membros = new HashSet<>();

  public void adicionarPessoa(Pessoa pessoa) {
    getMembros().add(pessoa);
  }

  public void removerPessoa(Pessoa pessoa) {
    getMembros().remove(pessoa);
  }
}
