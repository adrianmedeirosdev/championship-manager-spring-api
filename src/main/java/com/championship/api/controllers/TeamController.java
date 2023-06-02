package com.championship.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.championship.domain.model.Team;
import com.championship.domain.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teams")
public class TeamController {
  public final TeamService teamService;

  @Autowired
  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @GetMapping
  public List<Team> list(String name){
    if(name == null){
      return teamService.all();
    }else{
      return teamService.findBy(name);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Team> findBy(@PathVariable Integer id) {
    return teamService.findBy(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Team> create(@Valid @RequestBody Team team, UriComponentsBuilder builder){
    final Team savedTeam = teamService.save(team);
    final URI uri = builder
    .path("/teams/{id}")
    .buildAndExpand(savedTeam.getId()).toUri();
    return ResponseEntity.created(uri).body(savedTeam); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Team> update(@PathVariable Integer id, @Valid @RequestBody Team team){
    if(teamService.teamDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      team.setId(id);
      Team updatedTeam = teamService.save(team);
      return ResponseEntity.ok(updatedTeam);    
    }
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Team> optional = teamService.findBy(id );
        if (optional.isPresent()) {
            teamService.removeBy(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
