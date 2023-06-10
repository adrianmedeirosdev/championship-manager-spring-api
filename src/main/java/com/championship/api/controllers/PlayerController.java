package com.championship.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.championship.domain.model.Player;
import com.championship.domain.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {
  
  private final PlayerService service;

  @Autowired
  public PlayerController(PlayerService playerService) {
    this.service = playerService;
  }

  @GetMapping
  public List<Player> list(String name) {
    if (name == null) {
      return service.all();
    } else {
      return service.findBy(name);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Player> findBy(@PathVariable Integer id) {
    return service.findBy(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Player> create(@Valid @RequestBody Player player, UriComponentsBuilder builder){
    final Player savedPlayer = service.save(player);
    final URI uri = builder
    .path("/players/{id}")
    .buildAndExpand(savedPlayer.getId()).toUri();
    return ResponseEntity.created(uri).body(savedPlayer); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Player> update(@PathVariable Integer id, @Valid @RequestBody Player player){
    if(service.playerDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      player.setId(id);
      Player updatedPlayer = service.save(player);
      return ResponseEntity.ok(updatedPlayer);    
    }
  }

}
