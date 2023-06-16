package com.championship.api.dto.output;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerResponse {
  
  private Integer id;
  private String name;
  private LocalDate birth;
  private Float height;
  
}
