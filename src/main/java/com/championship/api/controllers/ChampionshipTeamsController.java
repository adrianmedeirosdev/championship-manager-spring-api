package com.championship.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.championship.api.dto.input.TeamIdRequest;
import com.championship.api.dto.output.TeamResponse;
import com.championship.api.mapper.TeamMapperAdapter;
import com.championship.domain.model.Team;
import com.championship.domain.service.AddTeamToChampionshipService;
import com.championship.domain.service.FindTeamsOfAChampionshipService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/championships/{id}/teams")
public class ChampionshipTeamsController {

  private final FindTeamsOfAChampionshipService findTeamsOfAChampionshipService;
  private final AddTeamToChampionshipService addTeamToChampionshipService;
  private final TeamMapperAdapter teamMapperAdapter;

  
  @GetMapping
  public List<TeamResponse> list(@PathVariable Integer id){
    List<Team> teams = findTeamsOfAChampionshipService.findTeams(id);
    return teamMapperAdapter.toCollectionModel(teams);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TeamResponse add(@PathVariable Integer id, @Valid @RequestBody TeamIdRequest teamIdRequest){
    Team addedTeam = addTeamToChampionshipService.add(id, teamMapperAdapter.toEntity(teamIdRequest));
    return teamMapperAdapter.toModelResponse(addedTeam);
  }
}
