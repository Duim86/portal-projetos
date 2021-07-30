package br.com.portaldeprojetos.api.controllers;

import br.com.portaldeprojetos.api.dtos.assembler.PessoaModelAssembler;
import br.com.portaldeprojetos.api.dtos.disassembler.PessoaInputDisassembler;
import br.com.portaldeprojetos.api.dtos.input.PessoaInput;
import br.com.portaldeprojetos.domain.exception.PessoaNaoEncontradaException;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import br.com.portaldeprojetos.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private PessoaModelAssembler pessoaModelAssembler;

  @Autowired
  private PessoaInputDisassembler pessoaInputDisassembler;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public ModelAndView listar() {

    var modelAndView = new ModelAndView("pessoas/index");
    var pessoas = pessoaModelAssembler.toCollectionModel(pessoaRepository.findAll());

    modelAndView.addObject("pessoas", pessoas);

    return modelAndView;
  }

  @GetMapping("/{pessoaId}")
  public ModelAndView show(@PathVariable Long pessoaId) {
    try {
      var pessoa = pessoaModelAssembler.toModel(pessoaService.buscarOuFalhar(pessoaId));
      var modelAndView = new ModelAndView("pessoas/show");
      modelAndView.addObject("pessoa", pessoa);

      return modelAndView;

    } catch (PessoaNaoEncontradaException e) {
      var modelAndView = new ModelAndView("error/404");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
      return modelAndView;
    }
  }

  @GetMapping("/new")
  public ModelAndView novo(PessoaInput pessoaInput) {
    var modelAndView = new ModelAndView("pessoas/new");
    modelAndView.addObject("pessoa", pessoaInput);

    return modelAndView;
  }


  @PostMapping("/salvar")
  public ModelAndView salvar(@Valid PessoaInput pessoaInput, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult);
      var modelAndView = new ModelAndView("pessoas/new");
      modelAndView.addObject(bindingResult);
      modelAndView.addObject("pessoa", pessoaInput);
      modelAndView.setStatus(HttpStatus.BAD_REQUEST);
      return modelAndView;
    }
    else {
      var pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
      pessoaService.salvar(pessoa);

      return new ModelAndView("redirect:/pessoas/" + pessoa.getId());
    }
  }

  @GetMapping("/{pessoaId}/edit")
  public ModelAndView atualizarPessoa(@PathVariable Long pessoaId){

    var pessoa = pessoaService.buscarOuFalhar(pessoaId);
    var modelAndView = new ModelAndView("pessoas/edit");
    modelAndView.addObject("pessoa", pessoa);
    modelAndView.addObject("pessoaId", pessoaId);

    return modelAndView;
  }

  @GetMapping("/{pessoaId}/delete")
  public ModelAndView delete(@PathVariable Long pessoaId) {
    var modelAndView = new ModelAndView("redirect:/pessoas");

    try {
      pessoaRepository.deleteById(pessoaId);
      modelAndView.addObject("mensagem", "Pessoa #" + pessoaId + " deletada com sucesso!");
      modelAndView.addObject("erro", false);
    }
    catch (EmptyResultDataAccessException e) {
      System.out.println(e);
      modelAndView = this.retornaErroPessoa("DELETE ERROR: Pessoa #" + pessoaId + " não encontrada no banco!");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
    }

    return modelAndView;
  }

  private ModelAndView retornaErroPessoa(String msg) {
    var modelAndView = new ModelAndView("redirect:/pessoas");
    modelAndView.addObject("mensagem", msg);
    modelAndView.addObject("erro", true);
    return modelAndView;
  }
}
