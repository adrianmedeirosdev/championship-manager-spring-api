package com.championship.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumRequest {
  
  @NotBlank
  private String name;

  @NotBlank
  private String address;


}
