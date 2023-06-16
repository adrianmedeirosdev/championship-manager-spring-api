package com.championship.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.championship.api.dto.input.MatchRequest;
import com.championship.api.dto.output.MatchResponse;
import com.championship.domain.model.Match;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MatchMapperAdapter {

  private ModelMapper modelMapper;

  public MatchResponse toModelResponse(Match match) {
    return modelMapper.map(match, MatchResponse.class);
  }

  public List<MatchResponse> toCollectionModel(List<Match> matchs) {
    return matchs.stream()
        .map(this::toModelResponse)
        .collect(Collectors.toList());
  }


  public Match toEntity(MatchRequest matchRequest) {
    return modelMapper.map(matchRequest, Match.class);
  }

}
