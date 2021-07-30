package br.com.portaldeprojetos.api.controllers;

import br.com.portaldeprojetos.api.dtos.assembler.ProjetoModelAssembler;
import br.com.portaldeprojetos.api.dtos.disassembler.ProjetoInputDisassembler;
import br.com.portaldeprojetos.api.dtos.input.PessoaInput;
import br.com.portaldeprojetos.api.dtos.input.ProjetoInput;
import br.com.portaldeprojetos.domain.exception.ProjetoNaoEncontradoException;
import br.com.portaldeprojetos.domain.repository.PessoaRepository;
import br.com.portaldeprojetos.domain.repository.ProjetoRepository;
import br.com.portaldeprojetos.domain.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ProjetoRepository projetoRepository;

  @Autowired ProjetoService projetoService;

  @Autowired
  private ProjetoModelAssembler projetoModelAssembler;

  @Autowired
  private ProjetoInputDisassembler projetoInputDisassembler;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public ModelAndView listar() {

    var modelAndView = new ModelAndView("projetos/index");
    var projetos = projetoModelAssembler.toCollectionModel(projetoRepository.findAll());

    modelAndView.addObject("projetos", projetos);

    return modelAndView;
  }
  @GetMapping("/{projetoId}")
  public ModelAndView show(@PathVariable Long projetoId) {
    try {
      var projeto = projetoModelAssembler.toModel(projetoService.buscarOuFalhar(projetoId));
      var funcionarios = pessoaRepository.findAllByFuncionarioIsTrue();
      var modelAndView = new ModelAndView("projetos/show");
      modelAndView.addObject("funcionarios", funcionarios);
      modelAndView.addObject("projeto", projeto);

      return modelAndView;

    } catch (ProjetoNaoEncontradoException e) {
      var modelAndView = new ModelAndView("error/404");
      modelAndView.setStatus(HttpStatus.NOT_FOUND);
      return modelAndView;
    }
  }

  @GetMapping("/new")
  public ModelAndView novo(ProjetoInput projetoInput) {
    var modelAndView = new ModelAndView("projetos/new");
    var pessoas = pessoaRepository.findAll();
    modelAndView.addObject("projeto", projetoInput);
    modelAndView.addObject("pessoas", pessoas);
    return modelAndView;
  }


  @PostMapping("/salvar")
  public ModelAndView salvar(@Valid ProjetoInput projetoInput, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult);
      var modelAndView = new ModelAndView("projetos/new");
      modelAndView.addObject(bindingResult);
      modelAndView.addObject("projeto", projetoInput);
      modelAndView.setStatus(HttpStatus.BAD_REQUEST);
      return modelAndView;
    }
    else {
      var projeto = projetoInputDisassembler.toDomainObject(projetoInput);
      projetoService.salvar(projeto);

      return new ModelAndView("redirect:/projetos/" + projeto.getId());
    }
  }

  @GetMapping("/{projetoId}/delete")
  public ModelAndView delete(@PathVariable Long projetoId) {
    var modelAndView = new ModelAndView("redirect:/projetos");

    projetoService.deleteById(projetoId);

    return modelAndView;
  }

  @PostMapping("/{pessoaId}/associar/{projetoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long pessoaId, @PathVariable Long projetoId) {
    projetoService.associarMembro(pessoaId, projetoId);
  }
}
