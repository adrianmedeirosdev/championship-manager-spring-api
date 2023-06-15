package com.championship.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.championship.domain.model.Player;
import com.championship.domain.repository.PlayerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class PlayerService {

  @Autowired
  private final PlayerRepository playerRepository;
  

public List<Player> all(){
  return playerRepository.findAll();
}

public Optional<Player> findBy(Integer id){
    return playerRepository.findById(id);
}

public List<Player> findBy(String name){
  return playerRepository.findByNameContaining(name);
}

public Page<Player> pagedSearch(Pageable page){
  return playerRepository.findAll(page);
}

public Page<Player> findBy(String name, Pageable page) {
        return playerRepository.findByNameContaining(name, page);
    }

@Transactional
public Player save(Player player){
  return playerRepository.save(player);
}


public boolean playerDoesNotExist(Integer id) {
  return !playerRepository.existsById(id);
}

}
