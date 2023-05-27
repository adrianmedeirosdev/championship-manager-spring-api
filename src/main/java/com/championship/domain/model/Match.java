package com.championship.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;


@Getter @Setter
@Entity
@ToString
public class Match {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private LocalDate date;
}
