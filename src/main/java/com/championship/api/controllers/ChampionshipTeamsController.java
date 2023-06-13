package com.championship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.championship.domain.model.Team;
import com.championship.domain.service.AddTeamToChampionshipService;
import com.championship.domain.service.FindTeamsOfAChampionshipService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/championships/{id}/teams")
public class ChampionshipTeamsController {

  @Autowired
  private final FindTeamsOfAChampionshipService findTeamsOfAChampionshipService;
  private final AddTeamToChampionshipService addTeamToChampionshipService;

  @GetMapping
  public List<Team> list(@PathVariable Integer championshId){
    List<Team> teams = findTeamsOfAChampionshipService.findTeams(championshId);
    return teams;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Team add(@PathVariable Integer championshipId, @Valid @RequestBody Team team){
    Team addedTeam = addTeamToChampionshipService.add(championshipId, team);
    return addedTeam;
  }
}
