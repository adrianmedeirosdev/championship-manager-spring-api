package com.championship.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.championship.domain.model.Player;

@Service
public class FindPlayersOfATeamService {
  private TeamService teamService;

  public List<Player> findPlayers(Integer teamId){
    return teamService.find(teamId).getPlayers();
  }
}
