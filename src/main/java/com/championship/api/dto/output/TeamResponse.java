package com.championship.api.dto.output;

import java.util.List;

import com.championship.domain.model.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponse {
  private Integer id;
  private String name;
  private List<Player> players;
}
