package br.com.portaldeprojetos.api.dtos.disassembler;

import br.com.portaldeprojetos.api.dtos.input.ProjetoInput;
import br.com.portaldeprojetos.domain.model.Projeto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Projeto toDomainObject(ProjetoInput projeto) {
    return modelMapper.map(projeto, Projeto.class);
  }

  public void copyToDomainObject(ProjetoInput projetoInput, Projeto projeto) {

    modelMapper.map(projetoInput, projeto);
  }
}
