package com.championship.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.championship.api.dto.input.StadiumRequest;
import com.championship.api.dto.output.StadiumResponse;
import com.championship.domain.model.Stadium;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StadiumMapperAdapter {

  private ModelMapper modelMapper;

  public StadiumResponse toModelResponse(Stadium stadium) {
    return modelMapper.map(stadium, StadiumResponse.class);
  }

  public List<StadiumResponse> toCollectionModel(List<Stadium> players) {
    return players.stream()
        .map(this::toModelResponse)
        .collect(Collectors.toList());
  }


  public Stadium toEntity(StadiumRequest stadiumRequest) {
    return modelMapper.map(stadiumRequest, Stadium.class);
  }

}
