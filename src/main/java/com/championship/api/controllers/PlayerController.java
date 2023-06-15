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

import com.championship.api.dto.input.PlayerRequest;
import com.championship.api.dto.output.PlayerResponse;
import com.championship.api.mapper.PlayerMapperAdapter;
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

  private final PlayerMapperAdapter playerMapperAdapter;

  @GetMapping
  public List<PlayerResponse> list(String name) {
    if (name == null) {
      return playerMapperAdapter.toCollectionModel(playerService.all());
    } else {
      return playerMapperAdapter.toCollectionModel(playerService.findBy(name));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerResponse> findBy(@PathVariable Integer id) {
    return playerService.findBy(id)
        .map(player -> ResponseEntity.ok(playerMapperAdapter.toModelResponse(player)))
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
  public ResponseEntity<PlayerResponse> create(@Valid @RequestBody PlayerRequest playerRequest, UriComponentsBuilder builder) {
    final Player savedPlayer = playerService.save(playerMapperAdapter.toEntity(playerRequest));
    final URI uri = builder
        .path("/players/{id}")
        .buildAndExpand(savedPlayer.getId()).toUri();
    return ResponseEntity.created(uri).body(playerMapperAdapter.toModelResponse(savedPlayer));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PlayerResponse> update(@PathVariable Integer id, @Valid @RequestBody PlayerRequest playerRequest) {
    if (playerService.playerDoesNotExist(id)) {
      return ResponseEntity.notFound().build();
    } else {
      final Player player = playerMapperAdapter.toEntity(playerRequest);
      player.setId(id);
      final Player updatedPlayer = playerService.save(playerMapperAdapter.toEntity(playerRequest));
      return ResponseEntity.ok(playerMapperAdapter.toModelResponse(updatedPlayer));
    }
  }

}
