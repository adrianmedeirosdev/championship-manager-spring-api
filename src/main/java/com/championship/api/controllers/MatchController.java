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

import com.championship.api.dto.input.MatchRequest;
import com.championship.api.dto.output.MatchResponse;
import com.championship.api.mapper.MatchMapperAdapter;
import com.championship.domain.model.Match;
import com.championship.domain.model.MatchStatus;
import com.championship.domain.service.MatchService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/matches")
public class MatchController {
  

  private final MatchService matchService;
  private final MatchMapperAdapter matchMapperAdapter;

  @GetMapping
  public List<MatchResponse> list(){
      return matchMapperAdapter.toCollectionModel(matchService.all());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MatchResponse> findBy(@PathVariable Integer id) {
    return matchService.findBy(id)
        .map(match -> ResponseEntity.ok(matchMapperAdapter.toModelResponse(match)))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{matchStatus}")
  public List<MatchResponse> list(@PathVariable String matchStatus) {
    List<Match> matches = matchService.findBy(matchStatus);
    return matchMapperAdapter.toCollectionModel(matches);
  }


  @PostMapping
  public ResponseEntity<MatchResponse> create(@Valid @RequestBody MatchRequest matchRequest, UriComponentsBuilder builder){
    final Match savedMatch = matchService.save(matchMapperAdapter.toEntity(matchRequest));
    savedMatch.setStatus(MatchStatus.PENDENTE);
    final URI uri = builder
    .path("/matches/{id}")
    .buildAndExpand(savedMatch.getId()).toUri();
    return ResponseEntity.created(uri).body(matchMapperAdapter.toModelResponse(savedMatch)); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<MatchResponse> update(@PathVariable Integer id, @Valid @RequestBody MatchRequest matchRequest){
    if(matchService.matchDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      matchMapperAdapter.toEntity(matchRequest).setId(id);
      Match updatedMatch = matchService.save(matchMapperAdapter.toEntity(matchRequest));
      return ResponseEntity.ok(matchMapperAdapter.toModelResponse(updatedMatch));    
    }
  }

}
