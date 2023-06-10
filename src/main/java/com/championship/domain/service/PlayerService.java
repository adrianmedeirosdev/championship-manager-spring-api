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

  private final PlayerRepository playerRepository;

  @Autowired
  public PlayerService(PlayerRepository repository) {
    this.playerRepository = repository;
  }

public List<Player> all(){
  return playerRepository.findAll();
}

public Optional<Player> findBy(Integer id){
    return playerRepository.findById(id);
}

public List<Player> findBy(String name){
  return playerRepository.findByNameContaining(name);
}

@Transactional
public Player save(Player player){
  return playerRepository.save(player);
}


public boolean playerDoesNotExist(Integer id) {
  return !playerRepository.existsById(id);
}

}
