package com.championship.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.championship.domain.model.Player;
import com.championship.domain.service.PlayerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

  @Autowired
  private final PlayerService playerService;

  @GetMapping
  public List<Player> list(String name) {
    if (name == null) {
      return playerService.all();
    } else {
      return playerService.findBy(name);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Player> findBy(@PathVariable Integer id) {
    return playerService.findBy(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/page")
  public Page<Player> list(@RequestParam(required = false) String name,
      @PageableDefault(sort = "name", direction = Sort.Direction.ASC, page = 0, size = 4) Pageable pagination) {
    if (name == null) {
      return playerService.pagedSearch(pagination);

    } else {
      return playerService.findBy(name, pagination);
    }
  }

  @PostMapping
  public ResponseEntity<Player> create(@Valid @RequestBody Player player, UriComponentsBuilder builder) {
    final Player savedPlayer = playerService.save(player);
    final URI uri = builder
        .path("/players/{id}")
        .buildAndExpand(savedPlayer.getId()).toUri();
    return ResponseEntity.created(uri).body(savedPlayer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Player> update(@PathVariable Integer id, @Valid @RequestBody Player player) {
    if (playerService.playerDoesNotExist(id)) {
      return ResponseEntity.notFound().build();
    } else {
      player.setId(id);
      Player updatedPlayer = playerService.save(player);
      return ResponseEntity.ok(updatedPlayer);
    }
  }

}
