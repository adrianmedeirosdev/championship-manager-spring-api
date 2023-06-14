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

import com.championship.domain.model.Match;
import com.championship.domain.service.MatchService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/matches")
public class MatchController {
  
  @Autowired
  private final MatchService matchService;

  @GetMapping
  public List<Match> list(){
      return matchService.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Match> findBy(@PathVariable Integer id) {
    return matchService.findBy(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Match> create(@Valid @RequestBody Match match, UriComponentsBuilder builder){
    final Match savedMatch = matchService.save(match);
    final URI uri = builder
    .path("/matches/{id}")
    .buildAndExpand(savedMatch.getId()).toUri();
    return ResponseEntity.created(uri).body(savedMatch); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Match> update(@PathVariable Integer id, @Valid @RequestBody Match match){
    if(matchService.matchDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      match.setId(id);
      Match updatedMatch = matchService.save(match);
      return ResponseEntity.ok(updatedMatch);    
    }
  }

}
