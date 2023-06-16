package com.championship.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChampionshipRequest {
  
  @NotBlank
  private String name;
  
  @NotNull
  private Integer year;

}
