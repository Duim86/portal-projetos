package br.com.portaldeprojetos.domain.repository;

import br.com.portaldeprojetos.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
