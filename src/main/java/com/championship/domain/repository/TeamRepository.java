package com.championship.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.championship.domain.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
  public List<Team> findByNameContaining(String name);
}
