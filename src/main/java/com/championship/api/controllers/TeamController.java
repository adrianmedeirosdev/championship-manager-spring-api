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

import com.championship.api.dto.input.TeamRequest;
import com.championship.api.dto.output.TeamResponse;
import com.championship.api.mapper.TeamMapperAdapter;
import com.championship.domain.model.Team;
import com.championship.domain.service.TeamService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController {
  
  private final TeamService teamService;
  private final TeamMapperAdapter teamMapperAdapter;

  @GetMapping
  public List<TeamResponse> list(String name){
    if(name == null){
      return teamMapperAdapter.toCollectionModel(teamService.all());
    }else{
      return teamMapperAdapter.toCollectionModel(teamService.findBy(name));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamResponse> findBy(@PathVariable Integer id) {
    return teamService.findBy(id)
        .map(team -> ResponseEntity.ok(teamMapperAdapter.toModelResponse(team)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest teamRequest, UriComponentsBuilder builder){
    final Team savedTeam = teamService.save(teamMapperAdapter.toEntity(teamRequest));
    final URI uri = builder
    .path("/teams/{id}")
    .buildAndExpand(savedTeam.getId()).toUri();
    return ResponseEntity.created(uri).body(teamMapperAdapter.toModelResponse(savedTeam)); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeamResponse> update(@PathVariable Integer id, @Valid @RequestBody TeamRequest teamRequest){
    if(teamService.teamDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      teamMapperAdapter.toEntity(teamRequest).setId(id);
      Team updatedTeam = teamService.save(teamMapperAdapter.toEntity(teamRequest));
      return ResponseEntity.ok(teamMapperAdapter.toModelResponse(updatedTeam));    
    }
  }

}
