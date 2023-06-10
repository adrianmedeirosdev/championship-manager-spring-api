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
public class Match {

  @EqualsAndHashCode.Include
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private Team homeTeam;

  @NotNull
  private Team awayTeam;

  @NotNull
  private LocalDate date;

  @NotNull
  private Stadium stadium;

  @NotNull
  private Result result;
}
