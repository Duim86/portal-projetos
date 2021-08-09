package br.com.portaldeprojetos.service;

import br.com.portaldeprojetos.domain.exception.NegocioException;
import br.com.portaldeprojetos.domain.exception.ProjetoNaoEncontradoException;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.model.Projeto;
import br.com.portaldeprojetos.domain.model.Status;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ProjetoPessoaServiceTest {

  private Long projetoId;
  private Long pessoaId;
  private Projeto projeto;
  private Pessoa pessoa;

  @InjectMocks
  private ProjetoPessoaService projetoPessoaService;

  @Mock
  private ProjetoService projetoService;

  @Mock
  private PessoaService pessoaService;

  @BeforeEach
  void setup() {
   projetoId = 1L;
   pessoaId = 1L;
   projeto = mock(Projeto.class);
   pessoa = Pessoa.builder()
            .build();
  }


  @Test
  void quandoAPessoaEFuncionarioEntaoPodeSerAdicionadaAoProjeto() {
    pessoa.setFuncionario(true);

    when(projetoService.buscarOuFalhar(projetoId)).thenReturn(projeto);
    when(pessoaService.buscarOuFalhar(pessoaId)).thenReturn(pessoa);

    projetoPessoaService.associarMembro(projetoId, pessoaId);

    verify(projeto, times(1)).adicionarPessoa(pessoa);

  }

  @Test
  void quandoAPessoaNaoEFuncionariaDeveFalharAoAdicionarAoProjeto() {

    pessoa.setFuncionario(false);

    when(projetoService.buscarOuFalhar(projetoId)).thenReturn(projeto);
    when(pessoaService.buscarOuFalhar(pessoaId)).thenReturn(pessoa);

    assertThrows(NegocioException.class, () -> projetoPessoaService.associarMembro(projetoId, pessoaId));
  }

  @Test
  void deveRemoverUmaPessoaDoProjeto() {
    when(projetoService.buscarOuFalhar(projetoId)).thenReturn(projeto);
    when(pessoaService.buscarOuFalhar(pessoaId)).thenReturn(pessoa);

    projetoPessoaService.desassociarMembro(projetoId, pessoaId);

    verify(projeto, times(1)).removerPessoa(pessoa);
  }
}