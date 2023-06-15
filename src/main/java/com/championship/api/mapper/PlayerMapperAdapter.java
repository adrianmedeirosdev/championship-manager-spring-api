package com.championship.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.championship.api.dto.input.PlayerRequest;
import com.championship.api.dto.output.PlayerResponse;
import com.championship.domain.model.Player;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PlayerMapperAdapter {

  private ModelMapper modelMapper;

  public PlayerResponse toModelResponse(Player player) {
    return modelMapper.map(player, PlayerResponse.class);
  }

  public List<PlayerResponse> toCollectionModel(List<Player> players) {
    return players.stream()
        .map(this::toModelResponse)
        .collect(Collectors.toList());
  }


  public Player toEntity(PlayerRequest playerRequest) {
    return modelMapper.map(playerRequest, Player.class);
  }

}
