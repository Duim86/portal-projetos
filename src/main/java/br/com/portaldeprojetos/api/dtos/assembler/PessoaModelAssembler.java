package br.com.portaldeprojetos.api.dtos.assembler;

import br.com.portaldeprojetos.api.dtos.model.PessoaListModel;
import br.com.portaldeprojetos.api.dtos.model.PessoaModel;
import br.com.portaldeprojetos.domain.model.Pessoa;
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

  public PessoaListModel toModelList(Pessoa pessoa) {
    return modelMapper.map(pessoa, PessoaListModel.class);
  }

  public List<PessoaListModel> toCollectionModel(List<Pessoa> pessoas){
    return pessoas.stream()
            .map(this::toModelList)
            .collect(Collectors.toList());
  }


}
