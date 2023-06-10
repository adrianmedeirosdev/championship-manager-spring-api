package com.championship.domain.model;

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
public class Team {

  @EqualsAndHashCode.Include
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank @Size(min = 2, max = 45)
  private String name;

  @NotNull
  private Stadium homeStadium;

}
