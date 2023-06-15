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
  @Column(name = "_year")
  private Integer year;

  @NotBlank @Size(min = 2, max = 45)
  @Column(name = "_name")
  private String name;
  
  @NotNull
  @ManyToMany(mappedBy = "championship")
  private List<Team> teams = new ArrayList<>();

  @NotNull
  @OneToMany(mappedBy = "championship")
  private List<Match> matches = new ArrayList<>();

  public Team add(Team team){
    this.getTeams().add(team);
    return team;
  }


}
