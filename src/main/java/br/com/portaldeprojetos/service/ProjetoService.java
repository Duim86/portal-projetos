package br.com.portaldeprojetos.service;

import br.com.portaldeprojetos.domain.exception.EntidadeEmUsoException;
import br.com.portaldeprojetos.domain.exception.NegocioException;
import br.com.portaldeprojetos.domain.exception.ProjetoNaoEncontradoException;
import br.com.portaldeprojetos.domain.model.Projeto;
import br.com.portaldeprojetos.domain.model.Status;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class ProjetoService {

  private static final String MSG_PROJETO_EM_USO = "Projeto de código %d não pode ser removido, pois está em uso";

  @Autowired
  private ProjetoRepository projetoRepository;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private ProjetoPessoaService projetoPessoaService;

  public Projeto buscarOuFalhar(Long projetoId) {
    return projetoRepository.findById(projetoId)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));
  }

  @Transactional
  public Projeto salvar(Projeto projeto) {
    var idgerente = projeto.getGerente().getId();
    var gerente = pessoaService.buscarOuFalhar(idgerente);
    projeto.setGerente(gerente);
    var projetoSalvo = projetoRepository.save(projeto);
    projetoPessoaService.associarMembro(projetoSalvo.getId(), idgerente);

    return projetoSalvo;
  }

  @Transactional
  public void remover(Long projetoId) {
    var projeto = buscarOuFalhar(projetoId);
    var status = Arrays.asList(Status.ENCERRADO, Status.INICIADO, Status.EM_ANDAMENTO);

    try {

      if(status.contains(projeto.getStatus())) {
        throw new NegocioException("O projeto não pode ser deletado");
      } else {
        projetoRepository.deleteById(projetoId);
        projetoRepository.flush();
      }

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
              String.format(MSG_PROJETO_EM_USO, projetoId));
    }
  }
}

