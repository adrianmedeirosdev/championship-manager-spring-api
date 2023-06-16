package com.championship.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Team {

  @EqualsAndHashCode.Include
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank @Size(min = 2, max = 45)
  @Column(name = "_name")
  private String name;

  @OneToOne
  private Stadium homeStadium;
  
  @OneToMany
  private List<Player> players = new ArrayList<>();

  @ManyToMany
  private List<Championship> championships = new ArrayList<>();

  public Player add(Player player){
    this.getPlayers().add(player);
    return player;
  }

}
