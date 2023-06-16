package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.championship.domain.exception.EntityNotFoundException;
import com.championship.domain.model.Team;
import com.championship.domain.repository.TeamRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TeamService {

  @Autowired
  public final TeamRepository teamRepository;


  public List<Team> all() {
    return teamRepository.findAll();
  }

  public Optional<Team> findBy(Integer id) {
    return teamRepository.findById(id);
  }

  public Team find(Integer id) {
    return teamRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Team not found."));
  }

  public List<Team> findBy(String name) {
    return teamRepository.findByNameContaining(name);
  }

  @Transactional
  public Team save(Team team) {
    return teamRepository.save(team);
  }

  public boolean teamDoesNotExist(Integer id) {
    return !teamRepository.existsById(id);
  }

}
