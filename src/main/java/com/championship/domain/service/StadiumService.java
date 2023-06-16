package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.championship.domain.exception.EntityNotFoundException;
import com.championship.domain.model.Stadium;
import com.championship.domain.repository.StadiumRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StadiumService {
  
  @Autowired
  private final StadiumRepository stadiumRepository;

  public List<Stadium> all(){
  return stadiumRepository.findAll();
}

public Optional<Stadium> findBy(Integer id){
    return stadiumRepository.findById(id);
}

public List<Stadium> findBy(String name){
  return stadiumRepository.findByNameContaining(name);
}

public Stadium find(Integer id) {
    return stadiumRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Stadium not found."));
  }

@Transactional
public Stadium save(Stadium stadium){
  return stadiumRepository.save(stadium);
}


public boolean stadiumDoesNotExist(Integer id) {
  return !stadiumRepository.existsById(id);
}


}
