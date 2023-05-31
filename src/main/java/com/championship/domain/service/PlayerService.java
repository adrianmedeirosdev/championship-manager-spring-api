package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.championship.domain.model.Player;
import com.championship.domain.repository.PlayerRepository;

import jakarta.transaction.Transactional;


@Service
public class PlayerService {

  private final PlayerRepository repository;

  @Autowired
  public PlayerService(PlayerRepository repository) {
    this.repository = repository;
  }

public List<Player> all(){
  return repository.findAll();
}

public Optional<Player> findBy(Integer id){
    return repository.findById(id);
}

public List<Player> findBy(String name){
  return repository.findByNameContaining(name);
}

@Transactional
public Player save(Player player){
  return repository.save(player);
}

@Transactional
public void removeBy(Integer id){
  repository.deleteById(id);
}

public boolean playerDoesntExist(Integer id) {
  return !repository.existsById(id);
}

}
