package br.com.portaldeprojetos.service;

import br.com.portaldeprojetos.domain.exception.EntidadeEmUsoException;
import br.com.portaldeprojetos.domain.exception.PessoaNaoEncontradaException;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

  private static final String MSG_PESSOA_EM_USO = "Pessoa de código %d não pode ser removida, pois está em uso";

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

  @Transactional
  public void remover(Long pessoaId) {
    try {
      pessoaRepository.deleteById(pessoaId);
      pessoaRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new PessoaNaoEncontradaException(pessoaId);

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
              String.format(MSG_PESSOA_EM_USO, pessoaId));
    }
  }
}
