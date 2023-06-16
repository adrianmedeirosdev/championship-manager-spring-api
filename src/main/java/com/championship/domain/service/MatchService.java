package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.championship.domain.exception.EntityNotFoundException;
import com.championship.domain.model.Match;
import com.championship.domain.repository.MatchRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MatchService {

  @Autowired
  private final MatchRepository matchRepository;

  public List<Match> all() {
    return matchRepository.findAll();
  }

  public Optional<Match> findBy(Integer id) {
    return matchRepository.findById(id);
  }

  public Match find(Integer id) {
    return matchRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Match not found."));
  }

  public List<Match> findBy(String status) {
    return matchRepository.findByStatusContaining(status);
  }

  @Transactional
  public Match save(Match match) {
    return matchRepository.save(match);
  }

  public boolean matchDoesNotExist(Integer id) {
    return !matchRepository.existsById(id);
  }

}
