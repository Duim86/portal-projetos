package br.com.portaldeprojetos.api.controllers.api;

import br.com.portaldeprojetos.service.ProjetoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos/{projetoId}")
public class MembrosController {

  @Autowired
  private ProjetoPessoaService projetoPessoaService;

  @PatchMapping("/associar/{pessoaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long projetoId, @PathVariable Long pessoaId) {
    projetoPessoaService.associarMembro(projetoId, pessoaId);
  }

  @PatchMapping ("/desassociar/{pessoaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long projetoId, @PathVariable Long pessoaId) {
    projetoPessoaService.desassociarMembro(projetoId, pessoaId);
  }
}
