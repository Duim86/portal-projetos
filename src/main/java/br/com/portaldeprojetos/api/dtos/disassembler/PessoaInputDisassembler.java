package br.com.portaldeprojetos.api.dtos.disassembler;

import br.com.portaldeprojetos.api.dtos.input.PessoaInput;
import br.com.portaldeprojetos.api.dtos.input.ProjetoInput;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.model.Projeto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Pessoa toDomainObject(PessoaInput pessoa) {
    return modelMapper.map(pessoa, Pessoa.class);
  }

  public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {

    modelMapper.map(pessoaInput, pessoa);
  }
}
