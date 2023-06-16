package com.championship.api.dto.output;

import com.championship.domain.model.MatchStatus;
import com.championship.domain.model.Result;
import com.championship.domain.model.Team;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MatchResponse {
  private Team homeTeam;
  private Team awayTeam;
  private MatchStatus status;
  private Result result;
}
