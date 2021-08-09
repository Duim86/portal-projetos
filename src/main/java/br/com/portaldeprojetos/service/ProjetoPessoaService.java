package br.com.portaldeprojetos.service;

import br.com.portaldeprojetos.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoPessoaService {

  @Autowired
  private ProjetoService projetoService;

  @Autowired
  private PessoaService pessoaService;

  @Transactional
  public void associarMembro(Long projetoId, Long pessoaId) {
    var projeto = projetoService.buscarOuFalhar(projetoId);
    var pessoa = pessoaService.buscarOuFalhar(pessoaId);
    if(Boolean.TRUE.equals(pessoa.getFuncionario())) {
      projeto.adicionarPessoa(pessoa);
    } else {
      throw new NegocioException("A pessoa deve ser um funcionário para ser adicionada");
    }
  }

  @Transactional
  public void desassociarMembro(Long projetoId, Long pessoaId) {
    var projeto = projetoService.buscarOuFalhar(projetoId);
    var pessoa = pessoaService.buscarOuFalhar(pessoaId);
    projeto.removerPessoa(pessoa);
  }
}
