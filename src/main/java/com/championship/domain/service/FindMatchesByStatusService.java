package com.championship.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.championship.domain.model.Match;
import com.championship.domain.model.MatchStatus;
import com.championship.domain.repository.MatchRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FindMatchesByStatusService {
  
  private final MatchRepository matchRepository;
  
  public List<Match> findBy(MatchStatus status) {
    return matchRepository.findByMatchStatusContaining(status);
  }

}
