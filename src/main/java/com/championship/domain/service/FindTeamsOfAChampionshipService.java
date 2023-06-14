package com.championship.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.championship.domain.model.Team;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FindTeamsOfAChampionshipService {
  
  
  private final ChampionshipService championshipService;
  
  public List<Team> findTeams(Integer championshipId) {
    return championshipService.find(championshipId)
        .getTeams();
  }

}
