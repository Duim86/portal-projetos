package br.com.portaldeprojetos.api.controllers.api;

import br.com.portaldeprojetos.api.dtos.assembler.ProjetoModelAssembler;
import br.com.portaldeprojetos.api.dtos.disassembler.ProjetoInputDisassembler;
import br.com.portaldeprojetos.api.dtos.input.ProjetoInput;
import br.com.portaldeprojetos.api.dtos.model.ProjetoListModel;
import br.com.portaldeprojetos.api.dtos.model.ProjetoModel;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import br.com.portaldeprojetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetosController {
  @Autowired
  private ProjetoRepository projetoRepository;

  @Autowired
  private ProjetoService projetoService;

  @Autowired
  private ProjetoModelAssembler projetoModelAssembler;

  @Autowired
  private ProjetoInputDisassembler projetoInputDisassembler;

  @GetMapping()
  public List<ProjetoListModel> listar() {
    return projetoModelAssembler.toCollectionModel(projetoRepository.findAll());
  }

  @GetMapping("/{projetoId}")
  public ProjetoModel buscarPorId(@PathVariable Long projetoId) {
    return projetoModelAssembler.toModel(projetoService.buscarOuFalhar(projetoId));
  }


  @PostMapping("/salvar")
  public ProjetoModel salvar(@RequestBody @Valid ProjetoInput projetoInput) {
    var projeto = projetoInputDisassembler.toDomainObject(projetoInput);
    return projetoModelAssembler.toModel(projetoService.salvar(projeto));
  }

  @DeleteMapping("/{projetoId}")
  public void remover(@PathVariable Long projetoId) {
    projetoService.remover(projetoId);
  }
}
