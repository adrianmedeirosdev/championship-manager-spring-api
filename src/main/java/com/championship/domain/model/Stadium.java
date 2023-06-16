package com.championship.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Stadium {
  
  @EqualsAndHashCode.Include
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank @Size(min = 5, max = 45)
  @Column(name = "_name")
  private String name;

  @NotBlank @Size(min = 50, max = 450)
  @Column(name = "_address")
  private String address;

  @OneToOne
  private Team team;

}
