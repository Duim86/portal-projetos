package br.com.portaldeprojetos.domain.repository;

import br.com.portaldeprojetos.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {
  List<Pessoa> findAllByFuncionarioIsTrue();
}
