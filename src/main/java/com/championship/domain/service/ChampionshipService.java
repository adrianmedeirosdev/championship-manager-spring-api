package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.championship.domain.exception.EntityNotFoundException;
import com.championship.domain.model.Championship;
import com.championship.domain.repository.ChampionshipRepository;

import jakarta.transaction.Transactional;

@Service
public class ChampionshipService {

  @Autowired
  private ChampionshipRepository championshipRepository;

  public List<Championship> all() {
    return championshipRepository.findAll();
  }

  public Optional<Championship> findBy(Integer id) {
    return championshipRepository.findById(id);
  }

  public Championship find(Integer id) {
    return championshipRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Championship not found."));
  }

  public List<Championship> findBy(String name){
    return championshipRepository.findByNameContaining(name);
  }

  @Transactional
  public Championship save(Championship championship) {
    return championshipRepository.save(championship);
  }

  public boolean championshipDoesNotExist(Integer id) {
  return !championshipRepository.existsById(id);
} 


}
