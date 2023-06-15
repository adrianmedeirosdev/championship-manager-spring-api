package com.championship.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.championship.domain.model.Match;
import com.championship.domain.service.MatchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/matches/{matchStatus}")
public class ChampionshipMatchesController {
  
  @Autowired
  private final MatchService matchService;


  @GetMapping
  public List<Match> list(@PathVariable String matchStatus) {
    List<Match> matches = matchService.findBy(matchStatus);
    return matches;
  }
  


}
