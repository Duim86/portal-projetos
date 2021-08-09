package br.com.portaldeprojetos.domain.model;

import lombok.*;

import javax.persistence.*;
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
public class Pessoa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(name = "datanascimento")
  private LocalDate dataNascimento;

  private String cpf;

  private Boolean funcionario;

  @ManyToMany(mappedBy = "membros")
  private Set<Projeto> projetos = new HashSet<>();
}
