package com.championship.api.dto.input;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRequest {

  @NotBlank
  private String name;
  
  @NotNull
  private LocalDate birth;

  @NotBlank
  private String gender;
  
  @NotNull
  private Float height;

}
