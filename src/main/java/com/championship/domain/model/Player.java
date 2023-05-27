package com.championship.domain.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter @Setter
@ToString
public class Player {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank @Size(min = 2, max = 20)
  private String name;

  @NotNull @Max(value = 10)
  private LocalDate birth;

  @NotBlank @Size(max = 10)
  private String gender;
  
  @NotNull @Positive @Min(value = (long) 1.2)
  private float height;

}
