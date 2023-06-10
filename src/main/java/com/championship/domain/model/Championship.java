package com.championship.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Championship {
  
  @EqualsAndHashCode.Include
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private Integer ano;

  @NotBlank @Size(min = 2, max = 45)
  private String name;
  
  @NotNull
  private List<Team> teams = new ArrayList<>();

  @NotNull
  private List<Match> matches = new ArrayList<>();

  public Team add(Team team){
    this.getTeams().add(team);
    return team;
  }


}
