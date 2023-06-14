package com.championship.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.Getter;

 
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "_match")
public class Match {

  @EqualsAndHashCode.Include
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @ManyToOne
  private Team homeTeam;

  @NotNull
  @ManyToOne
  private Team awayTeam;

  @NotNull
  @Column(name = "_date")
  private LocalDate date;

  @NotNull
  @Enumerated(EnumType.STRING)
  private MatchStatus status;

  @NotNull
  @ManyToOne
  private Championship championship;

  @Embedded
  private Result result;


}
