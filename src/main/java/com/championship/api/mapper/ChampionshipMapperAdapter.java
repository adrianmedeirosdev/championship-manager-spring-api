package com.championship.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.championship.api.dto.input.ChampionshipRequest;
import com.championship.api.dto.output.ChampionshipResponse;
import com.championship.domain.model.Championship;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ChampionshipMapperAdapter {
  
  private ModelMapper modelMapper;

  public ChampionshipResponse toModelResponse(Championship championship){
    return modelMapper.map(championship, ChampionshipResponse.class);
  }

  public List<ChampionshipResponse> toCollectionModel(List<Championship> championships) {
    return championships.stream()
        .map(this::toModelResponse)
        .collect(Collectors.toList());
  }


  public Championship toEntity(ChampionshipRequest championshipRequest) {
    return modelMapper.map(championshipRequest, Championship.class);
  }

  

}
