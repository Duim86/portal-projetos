package br.com.portaldeprojetos.domain.service;

import br.com.portaldeprojetos.domain.exception.PessoaNaoEncontradaException;
import br.com.portaldeprojetos.domain.exception.ProjetoNaoEncontradoException;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.model.Projeto;
import br.com.portaldeprojetos.domain.model.Status;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoService {

  @Autowired
  private ProjetoRepository projetoRepository;

  @Autowired
  private PessoaService pessoaService;

  public Projeto buscarOuFalhar(Long projetoId) {
    return projetoRepository.findById(projetoId)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));
  }

  @Transactional
  public Projeto salvar(Projeto projeto) {
    return projetoRepository.save(projeto);
  }

  @Transactional
  public void deleteById(Long projetoId) {
    var projeto = buscarOuFalhar(projetoId);

    if(projeto.getStatus() == Status.INICIADO || projeto.getStatus() == Status.EM_ANDAMENTO || projeto.getStatus() == Status.ENCERRADO) {
      throw new IllegalStateException("O projeto não pode ser deletado");
    } else {
      projetoRepository.deleteById(projetoId);
    }
  }

  @Transactional
  public void associarMembro(Long projetoId, Long pessoaId) {
    var projeto = buscarOuFalhar(projetoId);
    var pessoa = pessoaService.buscarOuFalhar(pessoaId);
    projeto.adicionarPessoa(pessoa);
  }
}
