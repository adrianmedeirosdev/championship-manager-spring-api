package com.championship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.championship.domain.model.Player;
import com.championship.domain.service.AddPlayerToATeamService;
import com.championship.domain.service.FindPlayersOfATeamService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/teams/{id}/players")
public class TeamPlayersController {

  @Autowired
  private final FindPlayersOfATeamService findPlayersOfATeamService;
  private final AddPlayerToATeamService addPlayerToATeamService;

  @GetMapping
  public List<Player> list(@PathVariable Integer teamId) {
    List<Player> players = findPlayersOfATeamService.findPlayers(teamId);
    return players;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Player add(@PathVariable Integer teamId, @Valid @RequestBody Player player) {
    Player addedPlayer = addPlayerToATeamService.add(teamId, player);
    return addedPlayer;
  }

}
