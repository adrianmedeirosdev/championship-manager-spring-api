package com.championship.domain.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Entity 
public class Player {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank @Size(min = 2, max = 45)
  @Column(name = "_name")
  private String name;

  @NotNull
  private LocalDate birth;

  @NotBlank @Size(max = 15)
  private String gender;
  
  @NotNull @Positive @Min(value = (long) 1.2)
  private float height;

}
