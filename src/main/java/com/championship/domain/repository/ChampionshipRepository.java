package com.championship.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.championship.domain.model.Championship;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer>{
  public List<Championship> findByNameContaining(String name);
}
