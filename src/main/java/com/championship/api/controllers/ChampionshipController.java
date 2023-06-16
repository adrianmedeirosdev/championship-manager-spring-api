package com.championship.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.championship.api.dto.input.ChampionshipRequest;
import com.championship.api.dto.output.ChampionshipResponse;
import com.championship.api.mapper.ChampionshipMapperAdapter;
import com.championship.domain.model.Championship;
import com.championship.domain.service.ChampionshipService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/championships")
public class ChampionshipController {

  
  private final ChampionshipService championshipService;
  private final ChampionshipMapperAdapter championshipMapperAdapter;

  @GetMapping
  public List<ChampionshipResponse> list(String name) {
    if (name == null) {
      return championshipMapperAdapter.toCollectionModel(championshipService.all());
    } else {
      return championshipMapperAdapter.toCollectionModel(championshipService.findBy(name));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ChampionshipResponse> find(@PathVariable Integer id) {
    return championshipService.findBy(id)
        .map(championship -> ResponseEntity.ok(championshipMapperAdapter.toModelResponse(championship)))
        .orElse(ResponseEntity.notFound().build());
  }

  

  @PostMapping
  public ResponseEntity<ChampionshipResponse> create(@Valid @RequestBody ChampionshipRequest championshipRequest, UriComponentsBuilder builder){
    final Championship savedChampionship = championshipService.save(championshipMapperAdapter.toEntity(championshipRequest));
    final URI uri = builder
    .path("/championships/{id}")
    .buildAndExpand(savedChampionship.getId()).toUri();
    return ResponseEntity.created(uri).body(championshipMapperAdapter.toModelResponse(savedChampionship));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ChampionshipResponse> update(@PathVariable Integer id, @Valid @RequestBody ChampionshipRequest championshipRequest){
    if(championshipService.championshipDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      championshipMapperAdapter.toEntity(championshipRequest).setId(id);
      Championship updatedChampionship = championshipService.save(championshipMapperAdapter.toEntity(championshipRequest));
      return ResponseEntity.ok(championshipMapperAdapter.toModelResponse(updatedChampionship));    
    }
  }

}
