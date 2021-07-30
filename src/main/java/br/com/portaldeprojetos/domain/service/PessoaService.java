package br.com.portaldeprojetos.domain.service;

import br.com.portaldeprojetos.domain.exception.PessoaNaoEncontradaException;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Transactional
  public Pessoa salvar(Pessoa pessoa) {
    return pessoaRepository.save(pessoa);
  }

  public Pessoa buscarOuFalhar(Long pessoaId) {
    return pessoaRepository.findById(pessoaId)
            .orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
  }
}
