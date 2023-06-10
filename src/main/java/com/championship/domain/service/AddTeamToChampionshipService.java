package com.championship.domain.service;

import org.springframework.stereotype.Service;

import com.championship.domain.model.Championship;
import com.championship.domain.model.Team;

import jakarta.transaction.Transactional;

@Service
public class AddTeamToChampionshipService {
  
  private ChampionshipService championshipService; 

  @Transactional
  public Team add(Integer championshipId, Team team){
    Championship championship = championshipService.find(championshipId);
    return championship.add(team);
  }

}
