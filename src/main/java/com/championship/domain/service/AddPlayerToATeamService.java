package com.championship.domain.service;

import org.springframework.stereotype.Service;

import com.championship.domain.model.Player;
import com.championship.domain.model.Team;

import jakarta.transaction.Transactional;

@Service
public class AddPlayerToATeamService {
  
  private TeamService teamService; 

  @Transactional
  public Player add(Integer teamId, Player player){
    Team team = teamService.find(teamId);
    return team.add(player);
  }

}
