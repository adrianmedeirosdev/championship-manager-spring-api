package com.championship.api.dto.input;

import java.time.LocalDate;

import com.championship.domain.model.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchRequest {
  
  private Team homeTeam;
  
  private Team awayTeam;
  
  private LocalDate date;

  private ChampionshipRequest championship;
  

}
