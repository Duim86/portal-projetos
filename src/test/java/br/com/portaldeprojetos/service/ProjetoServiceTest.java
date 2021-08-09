package br.com.portaldeprojetos.service;

import br.com.portaldeprojetos.domain.exception.NegocioException;
import br.com.portaldeprojetos.domain.exception.ProjetoNaoEncontradoException;
import br.com.portaldeprojetos.domain.model.Projeto;
import br.com.portaldeprojetos.domain.model.Status;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceTest {

  private Long projetoId;

  @InjectMocks
  private ProjetoService projetoService;

  @Mock
  private ProjetoRepository projetoRepository;

  @BeforeEach
  void setup() {
    projetoId = 1L;
  }

  @Test
  void deveFalharBuscarUmProjetoQuandoIdInexistente() {

    when(projetoRepository.findById(projetoId)).thenThrow(new ProjetoNaoEncontradoException(projetoId));

    assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.buscarOuFalhar(projetoId));
  }


  @Test
  void deveFalharAoRemoverProjetoComStatusIniciado() {
    Projeto projeto = Projeto.builder().status(Status.INICIADO).build();

    when(projetoRepository.findById(projetoId)).thenReturn(java.util.Optional.ofNullable(projeto));

    assertThrows(NegocioException.class, () -> projetoService.remover(projetoId));
  }

  @Test
  void deveFalharAoRemoverProjetoComStatusEncerrado() {
    Projeto projeto = Projeto.builder().status(Status.ENCERRADO).build();

    when(projetoRepository.findById(projetoId)).thenReturn(java.util.Optional.ofNullable(projeto));

    assertThrows(NegocioException.class, () -> projetoService.remover(projetoId));
  }

  @Test
  void deveFalharAoRemoverProjetoComStatusEmAndamento() {
    Projeto projeto = Projeto.builder().status(Status.EM_ANDAMENTO).build();

    when(projetoRepository.findById(projetoId)).thenReturn(java.util.Optional.ofNullable(projeto));

    assertThrows(NegocioException.class, () -> projetoService.remover(projetoId));
  }

  @Test
  void deveRemoverProjetoComStatusPlanejamento() {
    var projeto = Projeto.builder()
            .status(Status.PLANEJAMENTO).build();

    when(projetoRepository.findById(projetoId)).thenReturn(Optional.ofNullable(projeto));

    doNothing().when(projetoRepository).deleteById(projetoId);

    projetoService.remover(projetoId);


    verify(projetoRepository, times(1)).deleteById(projetoId);
  }

  @Test
  void deveFalharAoRemoverProjetoComIdInexistente() {

    when(projetoRepository.findById(projetoId)).thenThrow(new ProjetoNaoEncontradoException(projetoId));

    assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.remover(projetoId));


  }
}