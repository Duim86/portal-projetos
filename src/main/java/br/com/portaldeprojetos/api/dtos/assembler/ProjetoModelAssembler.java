package br.com.portaldeprojetos.api.dtos.assembler;

import br.com.portaldeprojetos.api.dtos.model.ProjetoListModel;
import br.com.portaldeprojetos.api.dtos.model.ProjetoModel;
import br.com.portaldeprojetos.domain.model.Projeto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProjetoModelAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public ProjetoModel toModel(Projeto projeto) {
    return modelMapper.map(projeto, ProjetoModel.class);
  }

  public ProjetoListModel toModelList(Projeto projeto) {
    return modelMapper.map(projeto, ProjetoListModel.class);
  }

  public List<ProjetoListModel> toCollectionModel(List<Projeto> projetos) {
    return projetos.stream()
            .map(this::toModelList)
            .collect(Collectors.toList());
  }


}
