package br.com.portaldeprojetos.api.controllers.api;

import br.com.portaldeprojetos.api.dtos.assembler.PessoaModelAssembler;
import br.com.portaldeprojetos.api.dtos.disassembler.PessoaInputDisassembler;
import br.com.portaldeprojetos.api.dtos.input.PessoaInput;
import br.com.portaldeprojetos.api.dtos.model.PessoaListModel;
import br.com.portaldeprojetos.api.dtos.model.PessoaModel;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import br.com.portaldeprojetos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoasController {
  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private PessoaModelAssembler pessoaModelAssembler;

  @Autowired
  private PessoaInputDisassembler pessoaInputDisassembler;

  @GetMapping
  public List<PessoaListModel> listar() {
    return pessoaModelAssembler.toCollectionModel(pessoaRepository.findAll());
  }

  @GetMapping("/{pessoaId}")
  public PessoaModel buscarPorId(@PathVariable Long pessoaId) {
    return pessoaModelAssembler.toModel(pessoaService.buscarOuFalhar(pessoaId));
  }

  @PostMapping
  public PessoaModel salvar(@RequestBody @Valid PessoaInput pessoaInput) {
    var pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
    return pessoaModelAssembler.toModel(pessoaService.salvar(pessoa));
  }

  @DeleteMapping("/{pessoaId}")
  public void remover(@PathVariable Long pessoaId) {
    pessoaService.remover(pessoaId);
  }
}
