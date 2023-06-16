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

import com.championship.api.dto.input.StadiumRequest;
import com.championship.api.dto.output.StadiumResponse;
import com.championship.api.mapper.StadiumMapperAdapter;
import com.championship.domain.model.Stadium;
import com.championship.domain.service.StadiumService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/stadiums")
public class StadiumController {
  
  private final StadiumService stadiumService;
  private final StadiumMapperAdapter stadiumMapperAdapter;

  @GetMapping
  public List<StadiumResponse> list(String name){
    if(name == null){
      return stadiumMapperAdapter.toCollectionModel(stadiumService.all());

    }else{
      return stadiumMapperAdapter.toCollectionModel(stadiumService.findBy(name));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<StadiumResponse> findBy(@PathVariable Integer id) {
    return stadiumService.findBy(id)
        .map(stadium -> ResponseEntity.ok(stadiumMapperAdapter.toModelResponse(stadium)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<StadiumResponse> create(@Valid @RequestBody StadiumRequest stadiumRequest, UriComponentsBuilder builder){
    final Stadium savedStadium = stadiumService.save(stadiumMapperAdapter.toEntity(stadiumRequest));
    final URI uri = builder
    .path("/stadiums/{id}")
    .buildAndExpand(savedStadium.getId()).toUri();
    return ResponseEntity.created(uri).body(stadiumMapperAdapter.toModelResponse(savedStadium)); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<StadiumResponse> update(@PathVariable Integer id, @Valid @RequestBody StadiumRequest stadiumRequest){
    if(stadiumService.stadiumDoesNotExist(id)){
      return ResponseEntity.notFound().build();
    } else {
      stadiumMapperAdapter.toEntity(stadiumRequest).setId(id);
      Stadium updatedStadium = stadiumService.save(stadiumMapperAdapter.toEntity(stadiumRequest));
      return ResponseEntity.ok(stadiumMapperAdapter.toModelResponse(updatedStadium));    
    }
  }

}
