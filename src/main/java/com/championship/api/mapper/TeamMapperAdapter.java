package com.championship.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.championship.api.dto.input.TeamIdRequest;
import com.championship.api.dto.input.TeamRequest;
import com.championship.api.dto.output.TeamResponse;
import com.championship.domain.model.Team;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TeamMapperAdapter {

  private ModelMapper modelMapper;

  public TeamResponse toModelResponse(Team team) {
    return modelMapper.map(team, TeamResponse.class);
  }

  public List<TeamResponse> toCollectionModel(List<Team> players) {
    return players.stream()
        .map(this::toModelResponse)
        .collect(Collectors.toList());
  }


  public Team toEntity(TeamRequest teamRequest) {
    return modelMapper.map(teamRequest, Team.class);
  }

    public Team toEntity(TeamIdRequest teamIdRequest) {
    return modelMapper.map(teamIdRequest, Team.class);
  }

}
