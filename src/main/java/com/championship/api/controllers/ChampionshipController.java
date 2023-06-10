package com.championship.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.championship.domain.model.Championship;
import com.championship.domain.service.ChampionshipService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/championships")
public class ChampionshipController {

  private final ChampionshipService championshipService;

  @GetMapping
  public List<Championship> list() {
    return championshipService.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Championship> find(@PathVariable Integer id) {
    return championshipService.findBy(id)
        .map(championship -> ResponseEntity.ok(championship))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Championship> register(@Valid @RequestBody Championship championship, UriComponentsBuilder builder){
    final Championship savedChampionship = championshipService.save(championship);
    final URI uri = builder
    .path("/championships/{id}")
    .buildAndExpand(savedChampionship.getId()).toUri();
    return ResponseEntity.created(uri).body(savedChampionship);
  }

}
