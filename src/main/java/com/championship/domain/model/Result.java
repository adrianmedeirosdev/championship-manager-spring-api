package com.championship.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {

  @NotNull 
  public Integer homeGoals;

  @NotNull
  public Integer awayGoals;


}
