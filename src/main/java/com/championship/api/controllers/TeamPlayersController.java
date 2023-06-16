package com.championship.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.championship.api.dto.input.PlayerIdRequest;
import com.championship.api.dto.output.PlayerResponse;
import com.championship.api.mapper.PlayerMapperAdapter;
import com.championship.domain.model.Player;
import com.championship.domain.service.AddPlayerToATeamService;
import com.championship.domain.service.FindPlayersOfATeamService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/teams/{id}/players")
public class TeamPlayersController {

  private final FindPlayersOfATeamService findPlayersOfATeamService;
  private final AddPlayerToATeamService addPlayerToATeamService;

  private PlayerMapperAdapter playerMapperAdapter;

  @GetMapping
  public List<PlayerResponse> list(@PathVariable Integer id) {
    List<PlayerResponse> players = playerMapperAdapter.toCollectionModel(findPlayersOfATeamService.findPlayers(id));
    return players;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PlayerResponse add(@PathVariable Integer id, @Valid @RequestBody PlayerIdRequest playerIdRequest) {
    Player addedPlayer = addPlayerToATeamService.add(id, playerMapperAdapter.toEntity(playerIdRequest));
    return playerMapperAdapter.toModelResponse(addedPlayer);
  }

}
