package com.championship.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerIdRequest {
  @NotNull
  private Integer id;
}
