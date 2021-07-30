package br.com.portaldeprojetos.api.dtos.assembler;

import br.com.portaldeprojetos.api.dtos.model.PessoaModel;
import br.com.portaldeprojetos.api.dtos.model.ProjetoModel;
import br.com.portaldeprojetos.domain.model.Pessoa;
import br.com.portaldeprojetos.domain.model.Projeto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PessoaModelAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public PessoaModel toModel(Pessoa pessoa) {
    return modelMapper.map(pessoa, PessoaModel.class);
  }

  public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas){
    return pessoas.stream()
            .map(this::toModel)
            .collect(Collectors.toList());
  }
}
