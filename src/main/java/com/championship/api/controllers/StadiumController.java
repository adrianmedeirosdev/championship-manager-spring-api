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

import com.championship.domain.model.Stadium;
import com.championship.domain.service.StadiumService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/stadiums")
public class StadiumController {
  
  @Autowired
  private final StadiumService stadiumService;

  @GetMapping
  public List<Stadium> list(){
      return stadiumService.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Stadium> findBy(@PathVariable Integer id) {
    return stadiumService.findBy(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Stadium> create(@Valid @RequestBody Stadium stadium, UriComponentsBuilder builder){
    final Stadium savedStadium = stadiumService.save(stadium);
    final URI uri = builder
    .path("/stadiums/{id}")
    .buildAndExpand(savedStadium.getId()).toUri();
    return ResponseEntity.created(uri).body(savedStadium); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Stadium> update(@PathVariable Integer id, @Valid @RequestBody Stadium stadium){
    if(stadiumService.stadiumDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      stadium.setId(id);
      Stadium updatedStadium = stadiumService.save(stadium);
      return ResponseEntity.ok(updatedStadium);    
    }
  }

}
