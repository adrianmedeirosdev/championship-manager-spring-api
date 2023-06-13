package com.championship.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Result {

  @NotNull 
  public Integer homeGoals;

  @NotNull
  public Integer awayGoals;

  public boolean gameDraw(Integer homeGoals, Integer awayGoals){
    return homeGoals == awayGoals ? true : false;
  }

}
